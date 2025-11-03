package com.tutorial.purchases.application.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PurchaseRequestDtos {

  @JsonProperty("purchases")
  private List<PurchaseRequestDto> purchaseRequestDtoList;
}
