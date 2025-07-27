package com.bahaaay.product.application.handler;

import com.bahaaay.product.application.dto.product.CreateProductRequest;
import com.bahaaay.product.application.dto.product.ProductDTO;
import com.bahaaay.product.application.dto.product.UpdateProductCommand;
import com.bahaaay.product.application.mapper.ProductDataMapper;
import com.bahaaay.product.domain.entity.Product;
import com.bahaaay.product.domain.repository.ProductRepository;
import com.bahaaay.product.interfaces.messaging.publisher.ProductEventPublisher;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ProductCommandHandler {

    private final ProductRepository productRepository;

    private final ProductDataMapper productDataMapper;

    private final ProductEventPublisher productEventPublisher;

    public ProductCommandHandler(ProductRepository productRepository, ProductDataMapper productDataMapper, ProductEventPublisher productEventPublisher) {
        this.productRepository = productRepository;
        this.productDataMapper = productDataMapper;
        this.productEventPublisher = productEventPublisher;
    }

    @Transactional
    public ProductDTO handleCreate(CreateProductRequest request) {

        // create a new product entity from the request
        Product product = productDataMapper.createProductRequestToProduct(request);

        // Save the product to the repository
        product = productRepository.save(product);

        // Publish product created event to Kafka topic
        productEventPublisher.publishCreated(product);

        return productDataMapper.productToProductDTO(product);

    }

    @Transactional
    public ProductDTO handleUpdate(UpdateProductCommand updateProductCommand) {
        // Retrieve the product by ID
        Product product = productRepository.findById(updateProductCommand.productId()).orElseThrow(
                () -> new EntityNotFoundException("Product not found with id: " + updateProductCommand.productId())
        );

        // Update product details
        product.updateName(updateProductCommand.name());
        product.updateDescription(updateProductCommand.description());
        product.updateCategory(updateProductCommand.category());
        product.updatePrice(updateProductCommand.price());

        // Save the updated product
        product = productRepository.save(product);

        // Publish product updated event to Kafka topic
        productEventPublisher.publishUpdated(product);

        return productDataMapper.productToProductDTO(product);
    }

}
