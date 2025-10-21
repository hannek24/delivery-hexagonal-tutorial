package com.tutorial.purchases.infra.purchases;

import com.tutorial.purchases.infra.purchases.models.PurchaseRequestEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
class PurchaseRequestRepository {

    private final List<PurchaseRequestEntity> purchaseRequestEntityList = new ArrayList<>();

    public PurchaseRequestEntity savePurchaseRequest(final PurchaseRequestEntity entity) {
        log.info("Saving purchase request entity: {}", entity);
        final var savedEntity = entity.toBuilder().id(UUID.randomUUID().toString()).build();

        purchaseRequestEntityList.add(savedEntity);

        log.info("Number of entities saved: {}", purchaseRequestEntityList.size());

        return savedEntity;
    }

    public List<PurchaseRequestEntity> getAll() {
        return new ArrayList<>(purchaseRequestEntityList);
    }
}
