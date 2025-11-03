package com.tutorial.purchases.infra.purchases.mappers;

import com.tutorial.purchases.domain.models.DomainPurchaseRequest;
import com.tutorial.purchases.infra.purchases.models.PurchaseRequestEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PurchaseRequestEntityMapper {
  PurchaseRequestEntity mapToEntity(DomainPurchaseRequest domainPurchaseRequest);

  DomainPurchaseRequest mapToDomainPurchaseRequest(PurchaseRequestEntity entity);
}
