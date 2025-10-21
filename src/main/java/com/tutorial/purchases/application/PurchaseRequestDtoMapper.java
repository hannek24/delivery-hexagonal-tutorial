package com.tutorial.purchases.application;

import com.tutorial.purchases.application.models.PurchaseRequestDto;
import com.tutorial.purchases.application.models.PurchaseRequestDtos;
import com.tutorial.purchases.domain.models.DomainPurchaseRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PurchaseRequestDtoMapper {
    DomainPurchaseRequest toDomainPurchaseRequest(PurchaseRequestDto purchaseRequestDto);
    PurchaseRequestDto    toPurchaseRequestDto(DomainPurchaseRequest domainPurchaseRequest);


    default PurchaseRequestDtos toPurchaseRequestDtos(List<DomainPurchaseRequest> domainPurchaseRequest){
        return PurchaseRequestDtos.builder()
                .purchaseRequestDtos(
                        domainPurchaseRequest.stream()
                        .map(this::toPurchaseRequestDto)
                        .toList())
                .build();
    }
}
