package com.bahaaay.product.application.handler;

import com.bahaaay.common.domain.valueobject.ProductId;
import com.bahaaay.product.application.dto.product.ProductDTO;
import com.bahaaay.product.application.mapper.ProductDataMapper;
import com.bahaaay.product.domain.repository.ProductRepository;
import org.springframework.stereotype.Component;

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
    public ProductDTO handleGetById(UUID id) {
        return productDataMapper.productToProductDTO(productRepository.findById(ProductId.from(id))); // Placeholder for actual implementation
    }
}
