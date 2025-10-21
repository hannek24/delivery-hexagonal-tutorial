package com.tutorial.purchases.domain.validators;

import com.tutorial.purchases.domain.exceptions.WrongPurchaseRequestException;
import com.tutorial.purchases.domain.models.DomainPurchaseRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class PurchaseRequestValidator {

    public void validate(final DomainPurchaseRequest domainPurchaseRequest) {

        // quantity must be greater than zero
        if (domainPurchaseRequest.getQuantity() <= 0) {
            throw new WrongPurchaseRequestException("Quantity must be greater than zero");
        }

        // sku cannot contain spaces
        if (domainPurchaseRequest.getSku().contains(" ")) {
            throw new WrongPurchaseRequestException("SKU cannot contain spaces");
        }

        // sku must be numbers
        if (!StringUtils.isNumeric(domainPurchaseRequest.getSku())) {
            throw new WrongPurchaseRequestException("SKU must be numeric");
        }
    }
}

