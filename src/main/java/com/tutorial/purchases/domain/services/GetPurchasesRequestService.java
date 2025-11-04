package com.tutorial.purchases.domain.services;

import com.tutorial.purchases.domain.models.DomainPurchaseRequest;
import com.tutorial.purchases.domain.ports.incoming.GetPurchasesRequestPort;
import com.tutorial.purchases.domain.ports.outgoing.RetrievePurchasesRequestPort;
import com.tutorial.purchases.infra.purchases.mappers.PurchaseRequestEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetPurchasesRequestService implements GetPurchasesRequestPort {

    private final RetrievePurchasesRequestPort retrievePurchasesRequestPort;
    /* HINT: this mapper live in the infra layer!
    *  We should not depend on the infra layer from within the domain layer!
    * */
    private final PurchaseRequestEntityMapper purchaseRequestMapper;

    @Override
    public List<DomainPurchaseRequest> getPurchases() {

        /*
         * HINT: this var is actually a:
         * List<com.tutorial.purchases.infra.purchases.models.PurchaseRequestEntity>
         * We should not depend on the infra layer from within the domain layer!
         */
        final var purchaseRequests = retrievePurchasesRequestPort.retrievePurchaseRequests();

        // Store the Purchase request
        final List<DomainPurchaseRequest> domainPurchaseRequests =
                purchaseRequests.stream()
                        .map(purchaseRequestMapper::mapToDomainPurchaseRequest)
                        .toList();
        log.info("Retrieved purchase requests: {}", domainPurchaseRequests);

        return domainPurchaseRequests;
    }
}
