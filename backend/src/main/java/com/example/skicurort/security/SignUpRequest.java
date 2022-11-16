package com.example.skicurort.security;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class SignUpRequest {
  String name;
  String username;
  String email;
  String password;
}
