package com.tutorial.purchases.application.controllers;

import com.tutorial.purchases.application.PurchaseRequestDtoMapper;
import com.tutorial.purchases.application.PurchaseResponseDtoMapper;
import com.tutorial.purchases.application.models.PurchaseRequestDto;
import com.tutorial.purchases.application.models.PurchaseResponseDto;
import com.tutorial.purchases.domain.ports.incoming.CreatePurchaseRequestPort;
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

    private final PurchaseRequestDtoMapper purchaseRequestDtoMapper;
    private final CreatePurchaseRequestPort createPurchaseRequestPort;
    private final PurchaseResponseDtoMapper purchaseResponseDtoMapper;

    @PostMapping("/purchase-requests")
    public final ResponseEntity<PurchaseResponseDto> createPurchaseRequest(@RequestBody PurchaseRequestDto purchaseRequestDto) {
        log.info("Purchase request received: {}", purchaseRequestDto);
        final var domainRequest = purchaseRequestDtoMapper.toDomainPurchaseRequest(purchaseRequestDto);

        // Create the Purchase request
        final var purchaseResponse = createPurchaseRequestPort.createPurchaseRequest(domainRequest);

        final var response = purchaseResponseDtoMapper.mapToResponse(purchaseResponse);
        return ResponseEntity.ok(response);
    }
}
