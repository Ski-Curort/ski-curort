package com.example.skicurort.security;

import java.util.Collection;
import java.util.Map;
import lombok.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Value
public class OAuthUserDetails extends User implements OAuth2User {
  String name;
  Map<String, Object> attributes;

  public OAuthUserDetails(
      String name,
      String username,
      String password,
      Collection<? extends GrantedAuthority> authorities,
      Map<String, Object> attributes) {
    super(username, password, authorities);
    this.name = name;
    this.attributes = attributes;
  }
}
