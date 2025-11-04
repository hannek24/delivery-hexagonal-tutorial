package com.tutorial.purchases.application.controllers;

import com.tutorial.purchases.application.models.PurchaseRequestDto;
import com.tutorial.purchases.infra.purchases.models.PurchaseRequestEntity;
import com.tutorial.purchases.services.CreatePurchaseRequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CreatePurchaseController {

  private final CreatePurchaseRequestService createPurchaseRequestService;

  @PostMapping("/purchase-requests")
  public final ResponseEntity<PurchaseRequestEntity> createPurchaseRequest(
      @RequestBody PurchaseRequestDto purchaseRequestDto) {
    log.info("Purchase request received: {}", purchaseRequestDto);

    // Create the Purchase request
    return createPurchaseRequestService.createPurchaseRequest(purchaseRequestDto);
  }
}
