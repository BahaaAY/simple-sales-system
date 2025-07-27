package com.bahaaay.sales.application.handler;

import com.bahaaay.common.application.dto.PagedResult;
import com.bahaaay.common.domain.valueobject.identifiers.ClientId;
import com.bahaaay.common.domain.valueobject.identifiers.SaleId;
import com.bahaaay.sales.application.dto.SaleDTO;
import com.bahaaay.sales.application.mapper.SalesDataMapper;
import com.bahaaay.sales.domain.entity.sales.Sale;
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

    public SalesQueryHandler(SalesDataMapper salesDataMapper, SalesRepository salesRepository) {
        this.salesDataMapper = salesDataMapper;
        this.salesRepository = salesRepository;
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
}
