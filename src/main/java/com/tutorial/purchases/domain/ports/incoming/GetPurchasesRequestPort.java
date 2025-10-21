package com.tutorial.purchases.domain.ports.incoming;

import com.tutorial.purchases.domain.models.DomainPurchaseRequest;
import com.tutorial.purchases.domain.models.DomainPurchaseResponse;

import java.util.List;

public interface GetPurchasesRequestPort {
    List<DomainPurchaseRequest> getPurchases();
}
