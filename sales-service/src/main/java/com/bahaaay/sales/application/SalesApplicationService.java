package com.bahaaay.sales.application;

import com.bahaaay.common.application.dto.PagedResult;
import com.bahaaay.common.domain.valueobject.identifiers.ClientId;
import com.bahaaay.common.domain.valueobject.identifiers.SaleId;
import com.bahaaay.sales.application.dto.SaleDTO;
import com.bahaaay.sales.application.dto.SaleTransactionLogDTO;
import com.bahaaay.sales.application.dto.create.CreateSaleRequest;
import com.bahaaay.sales.application.dto.update.UpdateSaleTransactionsCommand;
import com.bahaaay.sales.application.dto.update.UpdateSaleTransactionsRequest;
import com.bahaaay.sales.application.handler.SalesCommandHandler;
import com.bahaaay.sales.application.handler.SalesQueryHandler;
import com.bahaaay.sales.application.mapper.SalesDataMapper;
import com.bahaaay.sales.domain.entity.sales.Sale;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SalesApplicationService {

    private final SalesCommandHandler salesCommandHandler;
    private final SalesQueryHandler salesQueryHandler;

    private final SalesDataMapper salesDataMapper;


    public SalesApplicationService(SalesCommandHandler salesCommandHandler, SalesQueryHandler salesQueryHandler, SalesDataMapper salesDataMapper) {
        this.salesCommandHandler = salesCommandHandler;
        this.salesQueryHandler = salesQueryHandler;
        this.salesDataMapper = salesDataMapper;
    }
    public SaleDTO createSale(CreateSaleRequest request) {
        return salesCommandHandler.handleCreateSale(request);
    }

    public SaleDTO getSaleById(UUID id) {
        return salesQueryHandler.handleGetById(SaleId.from(id));
    }

    public PagedResult<SaleDTO> fetchSales(int page, int size, Optional<UUID> clientId) {
        return salesQueryHandler.handleFetchSales(
                page,
                size,
                clientId.map(ClientId::from)
        );
    }

    public SaleDTO updateSaleTransactions(UpdateSaleTransactionsRequest request, UUID saleId) {
        return salesCommandHandler.handleUpdateSaleTransactions(new UpdateSaleTransactionsCommand(SaleId.from(saleId), request.transactions())

        );
    }

    public List<SaleTransactionLogDTO> getSalesTransactionLogs(UUID id) {
        return salesQueryHandler.handleFetchLogsBySaleId(SaleId.from(id))
                .stream()
                .map(salesDataMapper::saleTransactionUpdateLogToSaleTransactionLogDTO)
                .toList();
    }
}
