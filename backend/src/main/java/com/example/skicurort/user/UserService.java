package com.example.skicurort.user;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  private static UserDetailsResponse toUserDetails(User user) {
    return UserDetailsResponse.builder()
        .displayName(user.getName())
        .username(user.getUsername())
        .email(user.getEmail())
        .roles(user.getRoles().stream().toList())
        .build();
  }

  public Optional<UserDetailsResponse> getUserDetails(String username) {
    return userRepository.findByUsernameOrEmail(username, username).map(UserService::toUserDetails);
  }

  public List<UserDetailsResponse> getAllUsers() {
    return userRepository.findAll(Sort.by("name")).stream()
        .map(UserService::toUserDetails)
        .toList();
  }
}
