package com.tutorial.purchases.application.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PurchaseResponseDto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("price")
    private BigDecimal price;
}

