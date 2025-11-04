package com.tutorial.purchases.domain.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tutorial.purchases.domain.models.DomainPurchaseRequest;
import com.tutorial.purchases.domain.models.DomainPurchaseResponse;
import com.tutorial.purchases.domain.ports.outgoing.StorePurchaseRequestPort;
import com.tutorial.purchases.domain.validators.PurchaseRequestValidator;
import java.math.BigDecimal;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreatePurchaseRequestServiceTest {

  @Mock private PurchaseRequestValidator purchaseRequestValidator;
  @Mock private StorePurchaseRequestPort storePurchaseRequestPort;
  @Mock private DomainPriceCalculator domainPriceCalculator;

  @InjectMocks private CreatePurchaseRequestService createPurchaseRequestService;

  @Mock private DomainPurchaseResponse domainPurchaseResponse;

  @Test
  void shouldStorePurchaseRequest() {
    final var request = Instancio.create(DomainPurchaseRequest.class);
    when(domainPriceCalculator.calculatePrice(request)).thenReturn(BigDecimal.ONE);
    when(storePurchaseRequestPort.storePurchaseRequest(request)).thenReturn(domainPurchaseResponse);

    // Run the test
    final var response = createPurchaseRequestService.createPurchaseRequest(request);

    // Verify the results
    assertThat(response).isEqualTo(domainPurchaseResponse);
    verify(purchaseRequestValidator).validate(request);
    verify(domainPriceCalculator).calculatePrice(request);
  }
}
