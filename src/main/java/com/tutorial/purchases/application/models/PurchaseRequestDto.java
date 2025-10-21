package com.tutorial.purchases.application.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PurchaseRequestDto {
    @JsonProperty("product")
    private String product;

    @JsonProperty("sku")
    private String sku;

    @JsonProperty("quantity")
    private int quantity;

}

