package com.bahaaay.product.application.handler;

import com.bahaaay.common.domain.valueobject.identifiers.ProductId;
import com.bahaaay.common.exception.ResourceNotFoundException;
import com.bahaaay.product.application.dto.product.ProductDTO;
import com.bahaaay.product.application.mapper.ProductDataMapper;
import com.bahaaay.product.domain.entity.Product;
import com.bahaaay.product.domain.repository.ProductRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
public class ProductQueryHandler {
    private final ProductRepository productRepository;

    private final ProductDataMapper productDataMapper;

    public ProductQueryHandler(ProductRepository productRepository, ProductDataMapper productDataMapper) {
        this.productRepository = productRepository;
        this.productDataMapper = productDataMapper;
    }
    /**
     * Handles the retrieval of a product by its ID.
     *
     * @param id the UUID of the product to retrieve
     * @return ProductDTO containing product details
     */
    @Transactional(readOnly = true)
    public ProductDTO handleGetById(UUID id) {

        Product product = productRepository.findById(ProductId.from(id))
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        // Convert the Product entity to ProductDTO using the mapper
        return productDataMapper.productToProductDTO(product);
    }
}
