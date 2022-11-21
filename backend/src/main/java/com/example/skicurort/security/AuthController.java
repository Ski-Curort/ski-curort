package com.example.skicurort.security;

import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Slf4j
// @CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

  @Autowired private AuthService authService;

  @PostMapping("/signin")
  @Transactional
  public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody SignInRequest loginDto) {
    JWTAuthResponse jwtAuthResponse = authService.authenticateUser(loginDto);
    return ResponseEntity.ok(jwtAuthResponse);
  }

  @PostMapping("/signup")
  @Transactional
  public ResponseEntity<SignUpResponse> registerUser(@RequestBody SignUpRequest signUpRequest) {
    try {
      String name = authService.signup(signUpRequest);
      return new ResponseEntity<>(
          SignUpResponse.success("User " + name + " registered successfully"), HttpStatus.OK);
    } catch (SignupException e) {
      return new ResponseEntity<>(SignUpResponse.failure(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
  }
}
