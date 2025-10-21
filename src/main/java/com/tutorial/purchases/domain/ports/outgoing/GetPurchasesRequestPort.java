package com.tutorial.purchases.domain.ports.outgoing;

import com.tutorial.purchases.domain.models.DomainPurchaseRequest;
import com.tutorial.purchases.domain.models.DomainPurchaseResponse;

import java.util.List;

public interface GetPurchasesRequestPort {
    List<DomainPurchaseRequest> storePurchaseRequest();
}

