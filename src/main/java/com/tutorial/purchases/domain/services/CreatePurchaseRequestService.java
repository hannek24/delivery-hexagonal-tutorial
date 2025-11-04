package com.tutorial.purchases.domain.services;

import com.tutorial.purchases.domain.models.DomainPurchaseRequest;
import com.tutorial.purchases.domain.models.DomainPurchaseResponse;
import com.tutorial.purchases.domain.validators.PurchaseRequestValidator;
import com.tutorial.purchases.infra.purchases.StorePurchaseRequestAdapter;
import com.tutorial.purchases.infra.purchases.mappers.PurchaseResponseMapper;
import com.tutorial.purchases.infra.purchases.models.PurchaseRequestEntity;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreatePurchaseRequestService {

  private final PurchaseRequestValidator purchaseRequestValidator;
  private final StorePurchaseRequestAdapter storePurchaseRequestAdapter;
  private final DomainPriceCalculator domainPriceCalculator;
  private final PurchaseResponseMapper purchaseResponseMapper;

  public DomainPurchaseResponse createPurchaseRequest(
      final DomainPurchaseRequest domainPurchaseRequest) {
    purchaseRequestValidator.validate(domainPurchaseRequest);

    // calculate price in domain
    /*
     * HINT: Calculating price done by separate domain service
     */
    final BigDecimal calculatedPrice = domainPriceCalculator.calculatePrice(domainPurchaseRequest);
    domainPurchaseRequest.setPrice(calculatedPrice);
    log.info("Handling purchase request (with price): {}", domainPurchaseRequest);

    final PurchaseRequestEntity entity =
        storePurchaseRequestAdapter.storePurchaseRequest(domainPurchaseRequest);

    /*
     * HINT: Mapping to domain response after storing the entity
     */
    final var domainPurchaseResponse = purchaseResponseMapper.mapToDomainPurchaseResponse(entity);

    log.info("Stored purchase request, response: {}", domainPurchaseResponse);
    return domainPurchaseResponse;
  }
}
