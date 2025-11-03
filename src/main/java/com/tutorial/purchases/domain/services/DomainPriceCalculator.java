package com.tutorial.purchases.domain.services;

import com.tutorial.purchases.domain.models.DomainPurchaseRequest;
import java.math.BigDecimal;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class DomainPriceCalculator {

  // Simple demo price list — domain logic can be expanded later
  private static final Map<String, BigDecimal> PRICE_LIST =
      Map.of(
          "1001", new BigDecimal("199.99"),
          "1002", new BigDecimal("299.99"),
          "2001", new BigDecimal("49.99"));

  private static final BigDecimal DEFAULT_UNIT_PRICE = new BigDecimal("99.99");

  public BigDecimal calculatePrice(final DomainPurchaseRequest request) {
    final var sku = request.getSku();
    final var unitPrice = PRICE_LIST.getOrDefault(sku, DEFAULT_UNIT_PRICE);
    return unitPrice.multiply(BigDecimal.valueOf(request.getQuantity()));
  }
}
