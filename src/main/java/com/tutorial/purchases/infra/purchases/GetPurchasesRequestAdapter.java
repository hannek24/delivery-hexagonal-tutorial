package com.tutorial.purchases.infra.purchases;

import com.tutorial.purchases.domain.models.DomainPurchaseRequest;
import com.tutorial.purchases.domain.ports.outgoing.GetPurchasesRequestPort;
import com.tutorial.purchases.infra.purchases.mappers.PurchaseRequestEntityMapper;
import com.tutorial.purchases.infra.purchases.mappers.PurchaseResponseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetPurchasesRequestAdapter implements GetPurchasesRequestPort {

    private final PurchaseRequestRepository purchaseRequestRepository;
    private final PurchaseRequestEntityMapper purchaseRequestMapper;
    private final PurchaseResponseMapper purchaseResponseMapper;

    @Override
    public List<DomainPurchaseRequest> getPurchaseRequests() {

        final var allEntities = purchaseRequestRepository.getAll();

        log.info("Found {} entities", allEntities.size());
        return allEntities.stream()
                .map(purchaseRequestMapper::mapToDomainPurchaseRequest)
                .toList();
    }
}
