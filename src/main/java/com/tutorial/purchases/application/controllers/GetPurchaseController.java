package com.tutorial.purchases.application.controllers;

import com.tutorial.purchases.application.controllers.models.PurchaseRequestEntity;
import com.tutorial.purchases.repository.PurchaseRequestRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class GetPurchaseController {

  private final PurchaseRequestRepository purchaseRequestRepository;

  @GetMapping("/purchases")
  public final ResponseEntity<List<PurchaseRequestEntity>> getPurchasesRequest() {
    log.info("getPurchasesRequest called");

    final var allEntities = purchaseRequestRepository.getAll();

    log.info("Retrieved {} purchase requests: {}", allEntities.size(), allEntities);

    return ResponseEntity.ok(allEntities);
  }
}
