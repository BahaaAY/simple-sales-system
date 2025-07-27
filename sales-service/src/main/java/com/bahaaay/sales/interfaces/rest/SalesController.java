package com.bahaaay.sales.interfaces.rest;


import com.bahaaay.sales.application.SalesApplicationService;
import com.bahaaay.sales.application.dto.SaleDTO;
import com.bahaaay.sales.application.dto.create.CreateSaleRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/sales")
public class SalesController {

    private final SalesApplicationService salesApplicationService;

    public SalesController(SalesApplicationService salesApplicationService) {
        this.salesApplicationService = salesApplicationService;
    }

    @PostMapping
    public ResponseEntity<SaleDTO> createSale(@RequestBody @Valid CreateSaleRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(salesApplicationService.createSale(request));
    }
}
