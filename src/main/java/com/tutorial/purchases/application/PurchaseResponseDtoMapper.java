package com.tutorial.purchases.application;

import com.tutorial.purchases.application.models.PurchaseResponseDto;
import com.tutorial.purchases.domain.models.DomainPurchaseResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PurchaseResponseDtoMapper {
  PurchaseResponseDto mapToResponse(DomainPurchaseResponse purchaseResponse);
}
