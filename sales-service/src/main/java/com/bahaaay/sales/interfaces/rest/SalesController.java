package com.bahaaay.sales.interfaces.rest;


import com.bahaaay.sales.application.SalesApplicationService;
import com.bahaaay.sales.application.dto.SaleDTO;
import com.bahaaay.sales.application.dto.create.CreateSaleRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @GetMapping("/{id}")
    public ResponseEntity<SaleDTO> getSaleById(@PathVariable("id") UUID id) {
        SaleDTO sale = salesApplicationService.getSaleById(id);
        return ResponseEntity.ok(sale);
    }
}
