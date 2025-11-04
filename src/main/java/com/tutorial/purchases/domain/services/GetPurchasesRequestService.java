package com.tutorial.purchases.domain.services;

import com.tutorial.purchases.domain.models.DomainPurchaseRequest;
import com.tutorial.purchases.infra.purchases.RetrievePurchasesRequestAdapter;
import com.tutorial.purchases.infra.purchases.mappers.PurchaseRequestEntityMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetPurchasesRequestService {

  private final RetrievePurchasesRequestAdapter retrievePurchasesRequestAdapter;
  private final PurchaseRequestEntityMapper purchaseRequestMapper;

  public List<DomainPurchaseRequest> getPurchases() {

    /*
     * HINT: Leak of infra model database entity. Hard to see.
     */
    final var purchaseRequests = retrievePurchasesRequestAdapter.retrievePurchaseRequests();

    // Store the Purchase request
    final List<DomainPurchaseRequest> domainPurchaseRequests =
        purchaseRequests.stream().map(purchaseRequestMapper::mapToDomainPurchaseRequest).toList();
    log.info("Retrieved purchase requests: {}", domainPurchaseRequests);

    return domainPurchaseRequests;
  }
}
