package com.tutorial.purchases.domain.services;

import com.tutorial.purchases.domain.models.DomainPurchaseRequest;
import com.tutorial.purchases.domain.ports.incoming.GetPurchasesRequestPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetPurchasesRequestService implements GetPurchasesRequestPort {

    private final com.tutorial.purchases.domain.ports.outgoing.GetPurchasesRequestPort getPurchasesRequestPort;

    @Override
    public List<DomainPurchaseRequest> getPurchases() {

        // Store the Purchase request
        List<DomainPurchaseRequest> domainPurchaseRequests =  getPurchasesRequestPort.getPurchaseRequests();

        return domainPurchaseRequests;
    }
}
