package com.bahaaay.product.application;

import com.bahaaay.product.application.dto.product.CreateProductRequest;
import com.bahaaay.product.application.dto.product.ProductDTO;
import com.bahaaay.product.application.handler.ProductCommandHandler;
import com.bahaaay.product.application.handler.ProductQueryHandler;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductApplicationService {

    private final ProductCommandHandler productCommandHandler;
    private final ProductQueryHandler productQueryHandler;

    public ProductApplicationService(ProductCommandHandler productCommandHandler, ProductQueryHandler productQueryHandler) {
        this.productCommandHandler = productCommandHandler;
        this.productQueryHandler = productQueryHandler;
    }

    public ProductDTO createProduct(CreateProductRequest createProductRequest)
    {
        return productCommandHandler.handleCreate(createProductRequest);
    }

    public ProductDTO getProductById(UUID id) {
        return productQueryHandler.handleGetById(id);
    }

}
