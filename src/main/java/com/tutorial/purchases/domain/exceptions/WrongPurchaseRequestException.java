package com.tutorial.purchases.domain.exceptions;

public class WrongPurchaseRequestException extends RuntimeException{
    public WrongPurchaseRequestException(final String message) {
        super(message);
    }

    public WrongPurchaseRequestException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public WrongPurchaseRequestException(final Throwable cause) {
        super(cause);
    }
}

