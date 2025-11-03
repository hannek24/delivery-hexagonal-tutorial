package com.tutorial.purchases.infra.purchases.mappers;

import com.tutorial.purchases.domain.models.DomainPurchaseResponse;
import com.tutorial.purchases.infra.purchases.models.PurchaseRequestEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PurchaseResponseMapper {
  DomainPurchaseResponse mapToDomainPurchaseResponse(PurchaseRequestEntity savedEntity);
}
