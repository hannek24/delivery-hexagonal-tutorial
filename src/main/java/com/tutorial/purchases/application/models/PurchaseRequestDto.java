package com.tutorial.purchases.application.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

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

