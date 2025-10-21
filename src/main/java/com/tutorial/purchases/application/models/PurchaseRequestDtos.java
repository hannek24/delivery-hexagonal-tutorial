package com.tutorial.purchases.application.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PurchaseRequestDtos {

    @JsonProperty("purchases")
    private List<PurchaseRequestDto> purchaseRequestDtos;
}

