package com.example.skicurort.user;

import com.example.skicurort.security.AuthService;
import com.example.skicurort.security.SignUpRequest;
import com.example.skicurort.security.SignupException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TestUsersInitializer implements InitializingBean {

  private final AuthService authService;
  private final UserRepository userRepository;

  private static User addAdminRole(User user) {
    user.getRoles().add(Role.ADMIN);
    return user;
  }

  @Override
  public void afterPropertiesSet() {
    initializeAdminUser();
  }

  private void initializeAdminUser() {
    initializeAdminUser(
        SignUpRequest.builder()
            .name("admin")
            .username("admin")
            .email("admin@jcw.pl")
            .password("admin")
            .build());
    initializeRegularUser(
        SignUpRequest.builder()
            .name("user")
            .username("user")
            .email("user")
            .password("user")
            .build());
  }

  private void initializeRegularUser(SignUpRequest request) {
    try {
      authService.signup(request);
    } catch (SignupException e) {
      log.info("Unable to initialize user {}", request.getUsername(), e);
    }
  }

  private void initializeAdminUser(SignUpRequest request) {
    try {
      authService.signup(request);
      userRepository
          .findByUsernameOrEmail(request.getUsername(), request.getEmail())
          .map(TestUsersInitializer::addAdminRole)
          .ifPresent(userRepository::save);
    } catch (SignupException e) {
      log.info("Unable to initialize user {}", request.getUsername(), e);
    }
  }
}
