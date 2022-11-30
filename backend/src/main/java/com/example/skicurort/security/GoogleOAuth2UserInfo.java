package com.example.skicurort.security;

import java.util.Map;
import lombok.Value;

@Value
public class GoogleOAuth2UserInfo {
  Map<String, Object> attributes;

  public String getId() {
    return (String) attributes.get("sub");
  }

  public String getName() {
    return (String) attributes.get("name");
  }

  public String getEmail() {
    return (String) attributes.get("email");
  }

  public String getImageUrl() {
    return (String) attributes.get("picture");
  }
}
