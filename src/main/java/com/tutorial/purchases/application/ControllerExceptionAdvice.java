package com.tutorial.purchases.application;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.tutorial.purchases.application.controllers.WrongPurchaseRequestException;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class ControllerExceptionAdvice extends ResponseEntityExceptionHandler {

  @ExceptionHandler({WrongPurchaseRequestException.class})
  @ResponseStatus(BAD_REQUEST)
  public Map<String, String> handleWrongPurchaseRequestException(
      final RuntimeException ex, final ServletWebRequest request) {

    log.error(
        "Handling domain validation exception for request {}: {}",
        request.getRequest().getRequestURI(),
        ex.getMessage());
    return Map.of("error", ex.getMessage());
  }
}
