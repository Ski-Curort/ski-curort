package com.example.skicurort.security;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SignUpResponse {
  String message;
  Status status;

  enum Status {
    SUCCESS,
    FAILURE
  }

  public static SignUpResponse success(String message) {
    return new SignUpResponse(message, Status.SUCCESS);
  }

  public static SignUpResponse failure(String message) {
    return new SignUpResponse(message, Status.FAILURE);
  }
}
