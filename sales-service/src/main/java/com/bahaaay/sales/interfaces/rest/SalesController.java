package com.bahaaay.sales.interfaces.rest;


import com.bahaaay.common.application.dto.PagedResult;
import com.bahaaay.sales.application.SalesApplicationService;
import com.bahaaay.sales.application.dto.SaleDTO;
import com.bahaaay.sales.application.dto.SaleTransactionLogDTO;
import com.bahaaay.sales.application.dto.create.CreateSaleRequest;
import com.bahaaay.sales.application.dto.update.UpdateSaleTransactionsRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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

    @GetMapping
    public ResponseEntity<PagedResult<SaleDTO>> list(
            @Valid @PositiveOrZero @RequestParam(name = "page", defaultValue = "0") int page,
            @Valid @Positive @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "client", required = false) UUID clientId
    ) {
        PagedResult<SaleDTO> result = salesApplicationService.fetchSales(
                page,
                size,
                Optional.ofNullable(clientId)
        );
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}/transactions")
    public ResponseEntity<SaleDTO> addTransactionToSale(
            @PathVariable("id") UUID id,
            @RequestBody @Valid UpdateSaleTransactionsRequest request
    ) {
        SaleDTO updatedSale = salesApplicationService.updateSaleTransactions(request, id);
        return ResponseEntity.ok(updatedSale);
    }

    @GetMapping("/{id}/logs")
    public ResponseEntity<List<SaleTransactionLogDTO>> getSaleLogs(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(salesApplicationService.getSalesTransactionLogs(id));
    }
}
