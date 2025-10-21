package com.tutorial.purchases.domain.ports.outgoing;

import com.tutorial.purchases.domain.models.DomainPurchaseRequest;
import com.tutorial.purchases.domain.models.DomainPurchaseResponse;

public interface StorePurchaseRequestPort {
    DomainPurchaseResponse storePurchaseRequest(DomainPurchaseRequest domainPurchaseRequest);
}

