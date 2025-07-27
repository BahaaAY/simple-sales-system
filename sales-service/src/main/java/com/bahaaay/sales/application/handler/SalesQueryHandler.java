package com.bahaaay.sales.application.handler;

import com.bahaaay.common.domain.valueobject.identifiers.SaleId;
import com.bahaaay.sales.application.dto.SaleDTO;
import com.bahaaay.sales.application.mapper.SalesDataMapper;
import com.bahaaay.sales.domain.repository.SalesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class SalesQueryHandler {
    private final SalesDataMapper salesDataMapper;

    private final SalesRepository salesRepository;

    public SalesQueryHandler(SalesDataMapper salesDataMapper, SalesRepository salesRepository) {
        this.salesDataMapper = salesDataMapper;
        this.salesRepository = salesRepository;
    }

    public SaleDTO handleGetById(SaleId from) {
        return salesDataMapper.saleToSaleDTO(
                salesRepository.findById(from)
                        .orElseThrow(() -> new EntityNotFoundException("Sale not found with id: " + from))
        );
    }
}
