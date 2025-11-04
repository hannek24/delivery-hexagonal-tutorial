package com.tutorial.purchases.services;

import com.tutorial.purchases.application.models.PurchaseRequestDto;
import com.tutorial.purchases.domain.exceptions.WrongPurchaseRequestException;
import com.tutorial.purchases.infra.purchases.PurchaseRequestRepository;
import com.tutorial.purchases.infra.purchases.models.PurchaseRequestEntity;
import java.math.BigDecimal;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreatePurchaseRequestService {
  // Simple demo price list — domain logic can be expanded later
  private static final Map<String, BigDecimal> PRICE_LIST =
      Map.of(
          "1001", new BigDecimal("199.99"),
          "1002", new BigDecimal("299.99"),
          "2001", new BigDecimal("49.99"));

  private static final BigDecimal DEFAULT_UNIT_PRICE = new BigDecimal("99.99");
  private final PurchaseRequestRepository purchaseRequestRepository;

  /*
   * HINT: returning Controller http Entity, domain should not know about that
   */
  public ResponseEntity<PurchaseRequestEntity> createPurchaseRequest(
      final PurchaseRequestDto purchaseRequestDto) {
    validate(purchaseRequestDto);

    final BigDecimal calculatedPrice = calculatePrice(purchaseRequestDto);
    purchaseRequestDto.setPrice(calculatedPrice);
    log.info("Handling purchase request: {}", purchaseRequestDto);

    final var entity =
        PurchaseRequestEntity.builder()
            .sku(purchaseRequestDto.getSku())
            .quantity(purchaseRequestDto.getQuantity())
            .price(purchaseRequestDto.getPrice())
            .product(purchaseRequestDto.getProduct())
            .build();

    // Saving the Purchase request:
    /*
     * HINT: Still direct dependency on repository
     */
    final var savedEntity = purchaseRequestRepository.savePurchaseRequest(entity);

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

  private BigDecimal calculatePrice(final PurchaseRequestDto purchaseRequestDto) {
    final var sku = purchaseRequestDto.getSku();
    final var unitPrice = PRICE_LIST.getOrDefault(sku, DEFAULT_UNIT_PRICE);
    return unitPrice.multiply(BigDecimal.valueOf(purchaseRequestDto.getQuantity()));
  }
}
