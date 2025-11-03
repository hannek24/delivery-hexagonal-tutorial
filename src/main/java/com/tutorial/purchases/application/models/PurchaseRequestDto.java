package com.tutorial.purchases.application.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class PurchaseRequestDto {
  @JsonProperty("id")
  private String id;

  @JsonProperty("product")
  private String product;

  @JsonProperty("sku")
  private String sku;

  @JsonProperty("quantity")
  private int quantity;

  @JsonProperty("price")
  private BigDecimal price;
}
