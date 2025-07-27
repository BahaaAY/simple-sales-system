package com.bahaaay.sales.application.handler;

import com.bahaaay.common.domain.valueobject.identifiers.ClientId;
import com.bahaaay.common.domain.valueobject.identifiers.ProductId;
import com.bahaaay.common.domain.valueobject.identifiers.SaleTransactionId;
import com.bahaaay.sales.application.dto.SaleDTO;
import com.bahaaay.sales.application.dto.create.CreateSaleRequest;
import com.bahaaay.sales.application.dto.update.UpdateSaleTransactionRequest;
import com.bahaaay.sales.application.dto.update.UpdateSaleTransactionsCommand;
import com.bahaaay.sales.application.mapper.SalesDataMapper;
import com.bahaaay.sales.domain.dto.TransactionQuantityUpdate;
import com.bahaaay.sales.domain.entity.client_ref.ClientRef;
import com.bahaaay.sales.domain.entity.product_ref.ProductRef;
import com.bahaaay.sales.domain.entity.sales.Sale;
import com.bahaaay.sales.domain.repository.ClientRefRepository;
import com.bahaaay.sales.domain.repository.ProductRefRepository;
import com.bahaaay.sales.domain.repository.SalesRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class SalesCommandHandler {
    private final ProductRefRepository productRefRepository;
    private final ClientRefRepository clientRefRepository;

    private final SalesRepository salesRepository;
    private final SalesDataMapper salesDataMapper;

    public SalesCommandHandler(ProductRefRepository productRefRepository, ClientRefRepository clientRefRepository, SalesRepository salesRepository, SalesDataMapper salesDataMapper) {
        this.productRefRepository = productRefRepository;
        this.clientRefRepository = clientRefRepository;
        this.salesRepository = salesRepository;
        this.salesDataMapper = salesDataMapper;
    }

    @Transactional
    public SaleDTO handleCreateSale(CreateSaleRequest request) {

        //load products ids
        List<ProductId> productIds = request.transactions().stream()
                .map(productId -> ProductId.from(productId.productId()))
                .toList();

        List<ProductRef> products = productRefRepository.findAllById(productIds);

        // check if all products were found
        if (products.size() != productIds.size()) {
            // figure out which ones are missing
            Set<ProductId> foundIds = products.stream()
                    .map(ProductRef::getId)
                    .collect(Collectors.toSet());
            // find the missing ones
            List<UUID> missing = productIds.stream()
                    .map(ProductId::getValue)
                    .filter(id -> !foundIds.contains(ProductId.from(id)))
                    .toList();
            throw new EntityNotFoundException(
                    "Product(s) not found: " + missing
            );
        }

        // check if client exists
        ClientRef client = clientRefRepository.findById(ClientId.from(request.clientId()))
                .orElseThrow(() -> new EntityNotFoundException(
                        "Client not found: " + request.clientId()
                ));

        // create a map of products by UUID, this is useful for quick lookups instead of looping over the list everytime
        Map<UUID, ProductRef> byUuid = products.stream()
                .collect(Collectors.toMap(
                        ref -> ref.getId().getValue(),
                        Function.identity()
                ));

        // create Sale
        Sale sale = Sale.create(
                client.getId(),
                request.seller()
        );

        // add transactions to the sale
        request.transactions().forEach(txReq -> {
            ProductRef ref = byUuid.get(txReq.productId());
            if (ref == null) {
                throw new EntityNotFoundException("Product not found: " + txReq.productId());
            }
            sale.addTransaction(ref, txReq.quantity());
        });
        // save the sale

        Sale savedSale = salesRepository.save(sale);

        return salesDataMapper.saleToSaleDTO(savedSale);
    }

    @Transactional
    public SaleDTO handleUpdateSaleTransactions(UpdateSaleTransactionsCommand updateSaleTransactionsCommand) {
        Sale sale = salesRepository.findById(
                updateSaleTransactionsCommand.saleId()
        ).orElseThrow(() -> new EntityNotFoundException(
                "Sale not found: " + updateSaleTransactionsCommand.saleId()
        ));
        // Batch‐update all lines
        for (UpdateSaleTransactionRequest txReq : updateSaleTransactionsCommand.transactionRequestList()) {
            // update transaction quantity from parent sale
            TransactionQuantityUpdate change = sale.updateTransactionQuantity(
                    SaleTransactionId.from(txReq.transactionId()),
                    txReq.newQuantity()
            );
            //TODO save log
        }
        // save the sale
        Sale savedSale = salesRepository.save(sale);

        // return the updated sale
        return salesDataMapper.saleToSaleDTO(savedSale);
    }
}
