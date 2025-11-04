package com.tutorial.purchases.repository;

import com.tutorial.purchases.application.controllers.models.PurchaseRequestEntity;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PurchaseRequestRepository {

  private static final List<PurchaseRequestEntity> PURCHASE_REQUESTS = new ArrayList<>();

  public PurchaseRequestEntity savePurchaseRequest(final PurchaseRequestEntity entity) {
    log.info("Saving purchase request entity: {}", entity);

    PURCHASE_REQUESTS.add(entity);

    log.info("Number of entities saved: {}", PURCHASE_REQUESTS.size());
    return entity;
  }

  public List<PurchaseRequestEntity> getAll() {
    return new ArrayList<>(PURCHASE_REQUESTS);
  }
}
