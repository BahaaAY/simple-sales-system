package com.bahaaay.product.interfaces.rest;

import com.bahaaay.product.application.ProductApplicationService;
import com.bahaaay.product.application.dto.product.ProductDTO;
import com.bahaaay.product.application.dto.product.CreateProductRequest;
import com.bahaaay.product.application.dto.product.UpdateProductRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductApplicationService productApplicationService;

    public ProductController(ProductApplicationService productApplicationService) {
        this.productApplicationService = productApplicationService;
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody CreateProductRequest createProductRequest) {
        ProductDTO productDTO = productApplicationService.createProduct(createProductRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") UUID id) {
        ProductDTO productDTO = productApplicationService.getProductById(id);
        return ResponseEntity.ok(productDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable("id") UUID id, @Valid @RequestBody UpdateProductRequest updateProductRequest) {
        ProductDTO productDTO = productApplicationService.updateProduct(id, updateProductRequest);
        return ResponseEntity.ok(productDTO);
    }

}
