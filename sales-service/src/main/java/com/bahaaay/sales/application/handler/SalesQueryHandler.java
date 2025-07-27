package com.bahaaay.sales.application.handler;

import com.bahaaay.common.application.dto.PagedResult;
import com.bahaaay.common.domain.valueobject.identifiers.ClientId;
import com.bahaaay.common.domain.valueobject.identifiers.SaleId;
import com.bahaaay.sales.application.dto.SaleDTO;
import com.bahaaay.sales.application.mapper.SalesDataMapper;
import com.bahaaay.sales.domain.entity.sales.Sale;
import com.bahaaay.sales.domain.entity.sales.SaleTransactionUpdateLog;
import com.bahaaay.sales.domain.repository.SaleTransactionUpdateLogRepository;
import com.bahaaay.sales.domain.repository.SalesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class SalesQueryHandler {
    private final SalesDataMapper salesDataMapper;

    private final SalesRepository salesRepository;

    private final SaleTransactionUpdateLogRepository saleTransactionUpdateLogRepository;

    public SalesQueryHandler(SalesDataMapper salesDataMapper, SalesRepository salesRepository, SaleTransactionUpdateLogRepository saleTransactionUpdateLogRepository) {
        this.salesDataMapper = salesDataMapper;
        this.salesRepository = salesRepository;
        this.saleTransactionUpdateLogRepository = saleTransactionUpdateLogRepository;
    }

    @Transactional(readOnly = true)
    public SaleDTO handleGetById(SaleId from) {
        return salesDataMapper.saleToSaleDTO(
                salesRepository.findById(from)
                        .orElseThrow(() -> new EntityNotFoundException("Sale not found with id: " + from))
        );
    }

    @Transactional(readOnly = true)
    public PagedResult<SaleDTO> handleFetchSales(int page, int size, Optional<ClientId> clientId) {

        int offset = page * size;
        List<Sale> salesList;
        long total;

        if (clientId.isPresent()) {
            salesList = salesRepository.findByClientId(clientId.get(), offset, size);
            total = salesRepository.countByClientId(clientId.get());
        }else
        {
            salesList = salesRepository.findAll(offset, size);
            total = salesRepository.countAll();

        }
        int totalPages = (int) Math.ceil((double) total / size);

        return new PagedResult<>(salesList.stream().map(salesDataMapper::saleToSaleDTO).toList(), page, size, total, totalPages);
    }

    @Transactional(readOnly = true)
    public List<SaleTransactionUpdateLog> handleFetchLogsBySaleId(SaleId saleId) {
        if (saleId == null) {
            throw new IllegalArgumentException("Sale ID cannot be null");
        }
        if (!salesRepository.existsById(saleId)) {
            throw new EntityNotFoundException("Sale not found with id: " + saleId);
        }
        return saleTransactionUpdateLogRepository.findBySaleId(saleId);
    }
}
