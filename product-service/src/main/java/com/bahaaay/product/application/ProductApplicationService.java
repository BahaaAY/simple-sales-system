package com.bahaaay.product.application;

import com.bahaaay.product.application.dto.product.CreateProductRequest;
import com.bahaaay.product.application.dto.product.ProductDTO;
import com.bahaaay.product.application.handler.ProductCommandHandler;
import org.springframework.stereotype.Service;

@Service
public class ProductApplicationService {

    private final ProductCommandHandler productCommandHandler;

    public ProductApplicationService(ProductCommandHandler productCommandHandler) {
        this.productCommandHandler = productCommandHandler;
    }

    public ProductDTO createProduct(CreateProductRequest createProductRequest)
    {
        return productCommandHandler.handleCreate(createProductRequest);
    }

}
