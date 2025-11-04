package com.tutorial.purchases.application.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.tutorial.purchases.application.controllers.models.PurchaseRequestDto;
import com.tutorial.purchases.application.controllers.models.PurchaseRequestEntity;
import com.tutorial.purchases.repository.PurchaseRequestRepository;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatusCode;

@ExtendWith(MockitoExtension.class)
class CreatePurchaseControllerTest {

  @Mock private PurchaseRequestRepository purchaseRequestRepository;

  @InjectMocks private CreatePurchaseController createPurchaseController;

  @Test
  void shouldCreatePurchaseRequest() {
    final PurchaseRequestDto dto =
        PurchaseRequestDto.builder().product("Awesome TV").sku("1001").quantity(2).build();

    when(purchaseRequestRepository.savePurchaseRequest(any(PurchaseRequestEntity.class)))
        .thenAnswer(invocation -> invocation.getArgument(0));

    final var response = createPurchaseController.createPurchaseRequest(dto);
    assertThat(response).isNotNull();

    final BigDecimal expectedPrice = new BigDecimal("199.99").multiply(BigDecimal.valueOf(2));

    assertThat(response.getBody())
        .isNotNull()
        .extracting(
            PurchaseRequestEntity::getSku,
            PurchaseRequestEntity::getProduct,
            PurchaseRequestEntity::getQuantity,
            PurchaseRequestEntity::getPrice)
        .containsExactly("1001", "Awesome TV", 2, expectedPrice);

    final ArgumentCaptor<PurchaseRequestEntity> captor =
        ArgumentCaptor.forClass(PurchaseRequestEntity.class);
    verify(purchaseRequestRepository).savePurchaseRequest(captor.capture());
    final PurchaseRequestEntity saved = captor.getValue();
    assertThat(saved)
        .extracting(
            PurchaseRequestEntity::getId,
            PurchaseRequestEntity::getSku,
            PurchaseRequestEntity::getProduct,
            PurchaseRequestEntity::getQuantity,
            PurchaseRequestEntity::getPrice)
        .containsExactly(response.getBody().getId(), "1001", "Awesome TV", 2, expectedPrice);
  }

  @Test
  void shouldCreatePurchaseRequest_withDefaultPrice() {

    final PurchaseRequestDto dto =
        PurchaseRequestDto.builder().product("Generic Item").sku("9999").quantity(3).build();

    when(purchaseRequestRepository.savePurchaseRequest(any(PurchaseRequestEntity.class)))
        .thenAnswer(invocation -> invocation.getArgument(0));

    final var response = createPurchaseController.createPurchaseRequest(dto);

    final BigDecimal expectedPrice = new BigDecimal("99.99").multiply(BigDecimal.valueOf(3));

    assertThat(response.getBody())
        .isNotNull()
        .extracting(
            PurchaseRequestEntity::getSku,
            PurchaseRequestEntity::getProduct,
            PurchaseRequestEntity::getQuantity,
            PurchaseRequestEntity::getPrice)
        .containsExactly("9999", "Generic Item", 3, expectedPrice);

    verify(purchaseRequestRepository, times(1))
        .savePurchaseRequest(any(PurchaseRequestEntity.class));
  }

  @Test
  void shouldThrowWrongPurchaseRequestException_whenInvalidateQuantityZero() {
    final PurchaseRequestDto dto =
        PurchaseRequestDto.builder().product("Bad Item").sku("1001").quantity(0).build();
    assertThrows(
        WrongPurchaseRequestException.class,
        () -> createPurchaseController.createPurchaseRequest(dto));

    verify(purchaseRequestRepository, never())
        .savePurchaseRequest(any(PurchaseRequestEntity.class));
  }

  @Test
  void shouldThrowWrongPurchaseRequestException_whenSkuContainsSpace() {

    final PurchaseRequestDto dto =
        PurchaseRequestDto.builder().product("Bad SKU").sku("10 01").quantity(1).build();
    assertThrows(
        WrongPurchaseRequestException.class,
        () -> createPurchaseController.createPurchaseRequest(dto));

    verify(purchaseRequestRepository, never())
        .savePurchaseRequest(any(PurchaseRequestEntity.class));
  }

  @Test
  void shouldThrowWrongPurchaseRequestException_whenSkuNonNumeric() {
    final PurchaseRequestDto dto =
        PurchaseRequestDto.builder().product("Alpha SKU").sku("ABC123").quantity(1).build();
    assertThrows(
        WrongPurchaseRequestException.class,
        () -> createPurchaseController.createPurchaseRequest(dto));

    verify(purchaseRequestRepository, never())
        .savePurchaseRequest(any(PurchaseRequestEntity.class));
  }

  @Test
  void shouldReturnInternalServerError_whenRepositoryThrowsException() {
    final PurchaseRequestDto dto =
        PurchaseRequestDto.builder().product("Faulty").sku("1001").quantity(1).build();

    when(purchaseRequestRepository.savePurchaseRequest(any(PurchaseRequestEntity.class)))
        .thenThrow(new RuntimeException("Database down"));

    final var response = createPurchaseController.createPurchaseRequest(dto);

    assertThat(response).isNotNull();
    assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(500));
    assertThat(response.getBody()).isNull();

    verify(purchaseRequestRepository).savePurchaseRequest(any(PurchaseRequestEntity.class));
  }
}
