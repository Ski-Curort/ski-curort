package com.example.skicurort.user;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @GetMapping({"/api/users/current-user", "/profile"})
  Optional<UserDetailsResponse> getCurentUser(HttpServletRequest request) {
    if (request.getUserPrincipal() instanceof Authentication auth && auth.isAuthenticated()) {
      return Optional.ofNullable(request.getUserPrincipal())
          .map(Principal::getName)
          .flatMap(userService::getUserDetails);
    }
    return Optional.empty();
  }

  @GetMapping("/api/users")
  @PreAuthorize("hasRole('ADMIN')")
  public List<UserDetailsResponse> getUsers() {
    return userService.getAllUsers();
  }
}
