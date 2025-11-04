package com.tutorial.purchases.application.controllers;

import com.tutorial.purchases.application.controllers.models.PurchaseRequestDto;
import com.tutorial.purchases.application.controllers.models.PurchaseRequestEntity;
import com.tutorial.purchases.repository.PurchaseRequestRepository;
import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class CreatePurchaseController {

  private static final Map<String, BigDecimal> PRICE_LIST =
      Map.of(
          "1001", new BigDecimal("199.99"),
          "1002", new BigDecimal("299.99"),
          "2001", new BigDecimal("49.99"));

  private static final BigDecimal DEFAULT_UNIT_PRICE = new BigDecimal("99.99");
  private final PurchaseRequestRepository purchaseRequestRepository;

  @PostMapping("/purchase-requests")
  public final ResponseEntity<PurchaseRequestEntity> createPurchaseRequest(
      @RequestBody PurchaseRequestDto purchaseRequestDto) {
    log.info("Purchase request received: {}", purchaseRequestDto);

    // Validate
    validate(purchaseRequestDto);

    // Calculate price
    final var sku = purchaseRequestDto.getSku();
    final var unitPrice = PRICE_LIST.getOrDefault(sku, DEFAULT_UNIT_PRICE);
    final BigDecimal calculatedPrice =
        unitPrice.multiply(BigDecimal.valueOf(purchaseRequestDto.getQuantity()));

    purchaseRequestDto.setPrice(calculatedPrice);
    log.info("Handling purchase request: {}", purchaseRequestDto);

    final var entity =
        PurchaseRequestEntity.builder()
            .id(UUID.randomUUID().toString())
            .sku(purchaseRequestDto.getSku())
            .quantity(purchaseRequestDto.getQuantity())
            .price(purchaseRequestDto.getPrice())
            .product(purchaseRequestDto.getProduct())
            .build();

    final PurchaseRequestEntity savedEntity;
    try {
      savedEntity = purchaseRequestRepository.savePurchaseRequest(entity);
    } catch (Exception e) {
      log.error("Error while saving purchase request: {}", e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    log.info("Stored purchase request: {}", savedEntity);
    return ResponseEntity.ok(savedEntity);
  }

  private void validate(final PurchaseRequestDto purchaseRequestDto) {

    // quantity must be greater than zero
    if (purchaseRequestDto.getQuantity() <= 0) {
      throw new WrongPurchaseRequestException("Quantity must be greater than zero");
    }

    // sku cannot contain spaces
    if (purchaseRequestDto.getSku().contains(" ")) {
      throw new WrongPurchaseRequestException("SKU cannot contain spaces");
    }

    // sku must be numbers
    if (!StringUtils.isNumeric(purchaseRequestDto.getSku())) {
      throw new WrongPurchaseRequestException("SKU must be numeric");
    }
  }
}
