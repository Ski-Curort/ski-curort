package com.example.skicurort.security;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
public class SignInRequest {
  String username;
  String password;
}
