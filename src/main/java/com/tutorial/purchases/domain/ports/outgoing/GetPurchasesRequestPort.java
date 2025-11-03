package com.tutorial.purchases.domain.ports.outgoing;

import com.tutorial.purchases.domain.models.DomainPurchaseRequest;
import java.util.List;

@FunctionalInterface
public interface GetPurchasesRequestPort {
  List<DomainPurchaseRequest> getPurchaseRequests();
}
