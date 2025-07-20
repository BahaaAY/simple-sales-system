package com.bahaaay.product.application.mapper;

import com.bahaaay.product.application.dto.product.CreateProductRequest;
import com.bahaaay.product.application.dto.product.ProductDTO;
import com.bahaaay.product.domain.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductDataMapper {

    public Product createProductRequestToProduct(
            CreateProductRequest createProductRequest
    ) {
        return Product.create(createProductRequest.name(), createProductRequest.description(), createProductRequest.category());
    }


    public ProductDTO productToProductDTO(Product product) {
        return new ProductDTO(
                product.getId().getValue(),
                product.getName(),
                product.getDescription(),
                product.getCategory(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }
}

