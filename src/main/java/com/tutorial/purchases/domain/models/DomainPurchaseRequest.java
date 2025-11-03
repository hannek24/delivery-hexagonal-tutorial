package com.tutorial.purchases.domain.models;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class DomainPurchaseRequest {
  private String id;
  private String product;
  private String sku;
  private int quantity;
  private BigDecimal price;
}
