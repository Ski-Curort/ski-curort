package com.example.skicurort.exception;

import org.springframework.http.HttpStatus;

public class SkiCurortApiException extends RuntimeException {
  private final HttpStatus status;

  public SkiCurortApiException(HttpStatus status, String message) {
    super(message);
    this.status = status;
  }

  public HttpStatus getStatus() {
    return status;
  }
}
