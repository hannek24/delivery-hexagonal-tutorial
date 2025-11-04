package com.tutorial.purchases.application.controllers;

import java.io.Serial;

public class WrongPurchaseRequestException extends RuntimeException {
  @Serial private static final long serialVersionUID = 1L;

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
