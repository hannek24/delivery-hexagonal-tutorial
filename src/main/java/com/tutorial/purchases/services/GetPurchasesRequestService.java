package com.tutorial.purchases.services;

import com.tutorial.purchases.infra.purchases.PurchaseRequestRepository;
import com.tutorial.purchases.infra.purchases.models.PurchaseRequestEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetPurchasesRequestService {

  private final PurchaseRequestRepository purchaseRequestRepository;

  public ResponseEntity<List<PurchaseRequestEntity>> getPurchases() {

    final var allEntities = purchaseRequestRepository.getAll();

    log.info("Retrieved {} purchase requests: {}", allEntities.size(), allEntities);

    return ResponseEntity.ok(allEntities);
  }
}
