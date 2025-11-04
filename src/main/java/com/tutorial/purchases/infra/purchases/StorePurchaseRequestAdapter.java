package com.tutorial.purchases.infra.purchases;

import com.tutorial.purchases.domain.models.DomainPurchaseRequest;
import com.tutorial.purchases.infra.purchases.mappers.PurchaseRequestEntityMapper;
import com.tutorial.purchases.infra.purchases.mappers.PurchaseResponseMapper;
import com.tutorial.purchases.infra.purchases.models.PurchaseRequestEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class StorePurchaseRequestAdapter {

  private final PurchaseRequestRepository purchaseRequestRepository;
  private final PurchaseRequestEntityMapper purchaseRequestMapper;
  private final PurchaseResponseMapper purchaseResponseMapper;

  public PurchaseRequestEntity storePurchaseRequest(
      final DomainPurchaseRequest domainPurchaseRequest) {
    log.info("Persisting purchase request for domainPurchaseRequest: {}", domainPurchaseRequest);
    final var entity = purchaseRequestMapper.mapToEntity(domainPurchaseRequest);

    // Saving the Purchase request:
    final var savedEntity = purchaseRequestRepository.savePurchaseRequest(entity);

    //    final var response = purchaseResponseMapper.mapToDomainPurchaseResponse(savedEntity);
    log.info("Returning purchase response: {}", savedEntity);
    return savedEntity;
  }
}
