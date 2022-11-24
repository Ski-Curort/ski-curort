package com.example.skicurort.security;

import com.example.skicurort.user.Role;
import com.example.skicurort.user.User;
import com.example.skicurort.user.UserRepository;
import java.util.Collections;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

  private final UserRepository userRepository;

  private final PasswordEncoder passwordEncoder;

  private final AuthenticationManager authenticationManager;

  private final JwtTokenProvider tokenProvider;

  public String signup(SignUpRequest signUpRequest) throws SignupException {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      throw new SignupException("Username is already taken!");
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      throw new SignupException("Email is already taken!");
    }

    User user = new User();
    user.setId(UUID.randomUUID());
    user.setName(signUpRequest.getName());
    user.setUsername(signUpRequest.getUsername());
    user.setEmail(signUpRequest.getEmail());
    user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
    user.setRoles(Collections.singleton(Role.USER));
    user = userRepository.save(user);
    log.info(
        "Created user: {}, username: {}, email: {}",
        user.getName(),
        user.getUsername(),
        user.getEmail());
    return user.getName();
  }

  public JWTAuthResponse authenticateUser(SignInRequest loginDto) {
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String token = tokenProvider.generateToken(authentication);
    return JWTAuthResponse.builder().accessToken(token).build();
  }
}
