package com.tutorial.purchases.infra.purchases;

import com.tutorial.purchases.domain.ports.outgoing.RetrievePurchasesRequestPort;
import com.tutorial.purchases.infra.purchases.models.PurchaseRequestEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RetrievePurchasesRequestAdapter implements RetrievePurchasesRequestPort {

  private final PurchaseRequestRepository purchaseRequestRepository;

  @Override
  public List<PurchaseRequestEntity> retrievePurchaseRequests() {

    final var allEntities = purchaseRequestRepository.getAll();

    log.info("Found {} entities", allEntities.size());

    return allEntities;
  }
}
