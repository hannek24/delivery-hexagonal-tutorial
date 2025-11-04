package com.tutorial.purchases.application.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.tutorial.purchases.application.PurchaseRequestDtoMapper;
import com.tutorial.purchases.application.PurchaseResponseDtoMapper;
import com.tutorial.purchases.application.models.PurchaseRequestDto;
import com.tutorial.purchases.application.models.PurchaseResponseDto;
import com.tutorial.purchases.domain.models.DomainPurchaseRequest;
import com.tutorial.purchases.domain.models.DomainPurchaseResponse;
import com.tutorial.purchases.domain.ports.incoming.CreatePurchaseRequestPort;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreatePurchaseControllerTest {
  @Mock private PurchaseRequestDtoMapper purchaseRequestDtoMapper;
  @Mock private CreatePurchaseRequestPort createPurchaseRequestPort;
  @Mock private PurchaseResponseDtoMapper purchaseResponseDtoMapper;
  @Mock private PurchaseResponseDto purchaseResponseDto;

  @InjectMocks private CreatePurchaseController createPurchaseController;
  @Mock private DomainPurchaseRequest domainPurchaseRequest;
  @Mock private DomainPurchaseResponse domainPurchaseResponse;

  @Test
  void shouldCallCreatePurchase() {

    final var purchaseRequestDto = Instancio.create(PurchaseRequestDto.class);

    when(purchaseRequestDtoMapper.toDomainPurchaseRequest(purchaseRequestDto))
        .thenReturn(domainPurchaseRequest);
    when(createPurchaseRequestPort.createPurchaseRequest(domainPurchaseRequest))
        .thenReturn(domainPurchaseResponse);
    when(purchaseResponseDtoMapper.mapToResponse(domainPurchaseResponse))
        .thenReturn(purchaseResponseDto);

    // Run the test
    final var response = createPurchaseController.createPurchaseRequest(purchaseRequestDto);

    // Verify the results
    assertThat(response).isNotNull();
    assertThat(response.getBody()).isEqualTo(purchaseResponseDto);
  }
}
