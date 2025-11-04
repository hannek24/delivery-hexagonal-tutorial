package com.tutorial.purchases.domain.services;

import com.tutorial.purchases.domain.models.DomainPurchaseRequest;
import com.tutorial.purchases.domain.models.DomainPurchaseResponse;
import com.tutorial.purchases.domain.ports.incoming.CreatePurchaseRequestPort;
import com.tutorial.purchases.domain.ports.outgoing.StorePurchaseRequestPort;
import com.tutorial.purchases.domain.validators.PurchaseRequestValidator;
import com.tutorial.purchases.infra.purchases.mappers.PurchaseResponseMapper;
import com.tutorial.purchases.infra.purchases.models.PurchaseRequestEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreatePurchaseRequestService implements CreatePurchaseRequestPort {

    private final PurchaseRequestValidator purchaseRequestValidator;
    private final StorePurchaseRequestPort storePurchaseRequestPort;
    private final DomainPriceCalculator domainPriceCalculator;
    private final PurchaseResponseMapper purchaseResponseMapper;


    @Override
    public DomainPurchaseResponse createPurchaseRequest(
            final DomainPurchaseRequest domainPurchaseRequest) {
        purchaseRequestValidator.validate(domainPurchaseRequest);

        // calculate price in domain
        final BigDecimal calculatedPrice = domainPriceCalculator.calculatePrice(domainPurchaseRequest);
        domainPurchaseRequest.setPrice(calculatedPrice);
        log.info("Handling purchase request (with price): {}", domainPurchaseRequest);

        /*
         * HINT: We should not depend on the infra model PurchaseRequestEntity
         */
        final PurchaseRequestEntity entity =
                storePurchaseRequestPort.storePurchaseRequest(domainPurchaseRequest);

        final var domainPurchaseResponse = purchaseResponseMapper.mapToDomainPurchaseResponse(entity);

        log.info("Stored purchase request, response: {}", domainPurchaseResponse);
        return domainPurchaseResponse;
    }
}
