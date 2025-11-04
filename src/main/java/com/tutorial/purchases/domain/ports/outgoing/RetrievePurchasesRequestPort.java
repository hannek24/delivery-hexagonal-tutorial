package com.tutorial.purchases.domain.ports.outgoing;

import com.tutorial.purchases.infra.purchases.models.PurchaseRequestEntity;
import java.util.List;

@FunctionalInterface
public interface RetrievePurchasesRequestPort {
  List<PurchaseRequestEntity> retrievePurchaseRequests();
}
