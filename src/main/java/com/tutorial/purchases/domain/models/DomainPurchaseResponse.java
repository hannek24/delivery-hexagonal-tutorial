package com.tutorial.purchases.domain.models;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DomainPurchaseResponse {
  private String id;
  private BigDecimal price;
}
