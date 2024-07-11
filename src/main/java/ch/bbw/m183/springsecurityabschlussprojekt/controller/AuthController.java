package ch.bbw.m183.springsecurityabschlussprojekt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.bbw.m183.springsecurityabschlussprojekt.model.request.LoginRequest;
import ch.bbw.m183.springsecurityabschlussprojekt.model.request.RegisterRequest;
import ch.bbw.m183.springsecurityabschlussprojekt.service.AuthService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<?> register(
      @RequestBody RegisterRequest registerRequest
  ) {
    return this.authService.register(registerRequest);
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(
      @RequestBody LoginRequest loginRequest
  ) {
    return this.authService.login(loginRequest);
  }
}
