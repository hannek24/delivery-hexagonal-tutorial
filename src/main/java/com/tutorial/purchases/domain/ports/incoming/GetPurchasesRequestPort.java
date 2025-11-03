package com.tutorial.purchases.domain.ports.incoming;

import com.tutorial.purchases.domain.models.DomainPurchaseRequest;

import java.util.List;

@FunctionalInterface
public interface GetPurchasesRequestPort {
    List<DomainPurchaseRequest> getPurchases();
}
