package com.example.skicurort.security;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class AuthControllerTest {

  @Autowired AuthController authController;

  @Test
  void shouldRegisterAndAuthenticate() {
    // give
    SignUpRequest signUpRequest = signUpRequest().build();
    SignInRequest signInRequest = signInRequest().build();

    // when
    ResponseEntity<?> responseEntity = authController.registerUser(signUpRequest);

    // then
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    // when
    ResponseEntity<JWTAuthResponse> jwtAuthResponseEntity =
        authController.authenticateUser(signInRequest);

    // then
    assertThat(jwtAuthResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(jwtAuthResponseEntity.getBody().getTokenType()).isEqualTo("Bearer");
    assertThat(jwtAuthResponseEntity.getBody().getAccessToken()).isNotNull();
    // TODO implement assertion decoding JWT token and checking values
  }

  private SignInRequest.SignInRequestBuilder signInRequest() {
    return signInRequest(signUpRequest());
  }

  private SignInRequest.SignInRequestBuilder signInRequest(
      SignUpRequest.SignUpRequestBuilder signupBuilder) {
    SignUpRequest signup = signupBuilder.build();
    return SignInRequest.builder().username(signup.getUsername()).password(signup.getPassword());
  }

  private SignUpRequest.SignUpRequestBuilder signUpRequest() {
    return SignUpRequest.builder()
        .email("test@gmail.com")
        .name("Test user")
        .username("test")
        .password("testpw");
  }
}
