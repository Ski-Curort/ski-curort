package com.example.skicurort.user;

import java.util.List;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
public class UserDetailsResponse {
  String displayName;
  String username;
  String email;
  List<Role> roles;
}
