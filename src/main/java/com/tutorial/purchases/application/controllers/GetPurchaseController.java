package com.tutorial.purchases.application.controllers;

import com.tutorial.purchases.application.PurchaseRequestDtoMapper;
import com.tutorial.purchases.application.models.PurchaseRequestDtos;
import com.tutorial.purchases.domain.ports.incoming.CreatePurchaseRequestPort;
import com.tutorial.purchases.domain.ports.incoming.GetPurchasesRequestPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class GetPurchaseController {

    private final PurchaseRequestDtoMapper purchaseRequestDtoMapper;
    private final CreatePurchaseRequestPort createPurchaseRequestPort;
    private final GetPurchasesRequestPort getPurchasesRequestPort;

    @GetMapping("/purchases")
    public final ResponseEntity<PurchaseRequestDtos> getPurchasesRequest() {
        log.info("getPurchasesRequest called");

        final var domainPurchaseRequests = getPurchasesRequestPort.getPurchases();

        return ResponseEntity.ok(
                purchaseRequestDtoMapper.toPurchaseRequestDtos(domainPurchaseRequests));
    }
}
