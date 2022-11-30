package com.example.skicurort.security;

import com.example.skicurort.user.Role;
import com.example.skicurort.user.User;
import com.example.skicurort.user.UserRepository;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService extends DefaultOAuth2UserService
    implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user =
        userRepository
            .findByUsernameOrEmail(username, username)
            .orElseThrow(
                () ->
                    new UsernameNotFoundException(
                        "User not found with username or email:" + username));
    return new org.springframework.security.core.userdetails.User(
        user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
  }

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    OAuth2User oAuth2User = super.loadUser(userRequest);
    GoogleOAuth2UserInfo googleOAuth2UserInfo =
        new GoogleOAuth2UserInfo(oAuth2User.getAttributes());

    if (!StringUtils.hasText(googleOAuth2UserInfo.getEmail())) {
      throw new OAuth2AuthenticationException("Email not found from provider");
    }

    User user =
        userRepository.save(
            userRepository
                .findByEmail(googleOAuth2UserInfo.getEmail())
                .orElseGet(this::newUser)
                .updateFromGoogle(googleOAuth2UserInfo));
    return new OAuthUserDetails(
        user.getName(),
        user.getUsername(),
        "",
        mapRolesToAuthorities(user.getRoles()),
        oAuth2User.getAttributes());
  }

  private User newUser() {
    User user = new User();
    user.setRoles(Set.of(Role.USER));
    user.setId(UUID.randomUUID());
    user.setPassword("");
    return user;
  }

  private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
    return roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.name())).toList();
  }
}
