package com.bahaaay.sales.application;

import com.bahaaay.sales.application.dto.SaleDTO;
import com.bahaaay.sales.application.dto.create.CreateSaleRequest;
import com.bahaaay.sales.application.handler.SalesCommandHandler;
import com.bahaaay.sales.application.mapper.SalesDataMapper;
import org.springframework.stereotype.Service;

@Service
public class SalesApplicationService {

    private final SalesCommandHandler salesCommandHandler;

    private final SalesDataMapper salesDataMapper;


    public SalesApplicationService(SalesCommandHandler salesCommandHandler, SalesDataMapper salesDataMapper) {
        this.salesCommandHandler = salesCommandHandler;
        this.salesDataMapper = salesDataMapper;
    }
    public SaleDTO createSale(CreateSaleRequest request) {
        return salesCommandHandler.handleCreateSale(request);
    }
}
