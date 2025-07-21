package com.bahaaay.product.application.handler;

import com.bahaaay.product.application.dto.product.CreateProductRequest;
import com.bahaaay.product.application.dto.product.ProductDTO;
import com.bahaaay.product.application.dto.product.UpdateProductCommand;
import com.bahaaay.product.application.mapper.ProductDataMapper;
import com.bahaaay.product.domain.entity.Product;
import com.bahaaay.product.domain.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class ProductCommandHandler {

    private final ProductRepository productRepository;

    private final ProductDataMapper productDataMapper;

    public ProductCommandHandler(ProductRepository productRepository, ProductDataMapper productDataMapper) {
        this.productRepository = productRepository;
        this.productDataMapper = productDataMapper;
    }

    public ProductDTO handleCreate(CreateProductRequest request) {

        Product product = productDataMapper.createProductRequestToProduct(request);

        product = productRepository.save(product);

        //TODO publish product created event

        return productDataMapper.productToProductDTO(product);

    }

    public ProductDTO handleUpdate(UpdateProductCommand updateProductCommand) {
        Product product = productRepository.findById(updateProductCommand.productId()).orElseThrow(
                () -> new EntityNotFoundException("Product not found with id: " + updateProductCommand.productId())
        );

        product.updateName(updateProductCommand.name());
        product.updateDescription(updateProductCommand.description());
        product.updateCategory(updateProductCommand.category());
        product.updatePrice(updateProductCommand.price());

        product = productRepository.save(product);

        //TODO publish product updated event

        return productDataMapper.productToProductDTO(product);
    }


}
