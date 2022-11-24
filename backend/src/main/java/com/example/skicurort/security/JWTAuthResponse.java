package com.example.skicurort.security;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class JWTAuthResponse {

  String accessToken;
  @Builder.Default String tokenType = "Bearer";

  public static JWTAuthResponse bearer(String token) {
    return new JWTAuthResponse(token, "Bearer");
  }
}
