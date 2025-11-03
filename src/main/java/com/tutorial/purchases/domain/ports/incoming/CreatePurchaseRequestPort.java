package com.tutorial.purchases.domain.ports.incoming;

import com.tutorial.purchases.domain.models.DomainPurchaseRequest;
import com.tutorial.purchases.domain.models.DomainPurchaseResponse;

@FunctionalInterface
public interface CreatePurchaseRequestPort {
  DomainPurchaseResponse createPurchaseRequest(DomainPurchaseRequest domainPurchaseRequest);
}
