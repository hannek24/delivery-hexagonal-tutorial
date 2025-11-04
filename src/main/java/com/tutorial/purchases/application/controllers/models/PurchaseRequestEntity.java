package com.tutorial.purchases.application.controllers.models;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class PurchaseRequestEntity {
  private String id;
  private String product;
  private String sku;
  private int quantity;
  private BigDecimal price;
}
