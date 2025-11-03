package com.tutorial.purchases.application.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class PurchaseResponseDto {

  @JsonProperty("id")
  private String id;

  @JsonProperty("price")
  private BigDecimal price;
}
