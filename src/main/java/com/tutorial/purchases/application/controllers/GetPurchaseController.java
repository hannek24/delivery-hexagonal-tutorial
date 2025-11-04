package com.tutorial.purchases.application.controllers;

import com.tutorial.purchases.infra.purchases.models.PurchaseRequestEntity;
import com.tutorial.purchases.services.GetPurchasesRequestService;
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

  private final GetPurchasesRequestService getPurchasesRequestService;

  @GetMapping("/purchases")
  public final ResponseEntity<List<PurchaseRequestEntity>> getPurchasesRequest() {
    log.info("getPurchasesRequest called");

    return getPurchasesRequestService.getPurchases();
  }
}
