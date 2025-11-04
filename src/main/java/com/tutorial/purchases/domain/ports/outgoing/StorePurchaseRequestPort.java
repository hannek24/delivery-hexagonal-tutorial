package com.tutorial.purchases.domain.ports.outgoing;

import com.tutorial.purchases.domain.models.DomainPurchaseRequest;
import com.tutorial.purchases.domain.models.DomainPurchaseResponse;
import com.tutorial.purchases.infra.purchases.models.PurchaseRequestEntity;

@FunctionalInterface
public interface StorePurchaseRequestPort {
    PurchaseRequestEntity storePurchaseRequest(DomainPurchaseRequest domainPurchaseRequest);
}
