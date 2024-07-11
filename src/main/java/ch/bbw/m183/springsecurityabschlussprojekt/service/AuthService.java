package ch.bbw.m183.springsecurityabschlussprojekt.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ch.bbw.m183.springsecurityabschlussprojekt.model.User;
import ch.bbw.m183.springsecurityabschlussprojekt.model.FailedLoginAttempt;
import ch.bbw.m183.springsecurityabschlussprojekt.model.request.LoginRequest;
import ch.bbw.m183.springsecurityabschlussprojekt.model.request.RegisterRequest;
import ch.bbw.m183.springsecurityabschlussprojekt.model.response.LoginResponse;
import ch.bbw.m183.springsecurityabschlussprojekt.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
@Service
public class AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private static final Set<String> WEAK_PASSWORDS = Set.of("Password1", "123456", "admin", "admin123", "password", "test", "test123", "1234");
  private static final int MAX_ATTEMPTS = 5;
  private final Map<String, FailedLoginAttempt> loginAttempts = new HashMap<>();
  private static final long LOCKOUT_DURATION = 60 * 60 * 1000;

  public ResponseEntity<?> register(RegisterRequest registerRequest) {
    if (isWeakPassword(registerRequest.getPassword())) {
      log.warn("weak password");
      return ResponseEntity.status(400).body("Weak password");
    }
    if (!userRepository.existsByUsername(registerRequest.getUsername())) {
      User user = new User(registerRequest.getUsername(),  passwordEncoder.encode(registerRequest.getPassword()), registerRequest.getFirstname(), registerRequest.getLastname());
      log.info("user registered");
      return ResponseEntity.ok().body(this.userRepository.save(user));
    } else {
      log.warn("username is already taken: {}", registerRequest.getUsername());
      return ResponseEntity.status(409).body("username is already taken");
    }
  }

  private boolean isWeakPassword(String password) {
    return WEAK_PASSWORDS.contains(password);
  }

  public ResponseEntity<?> login(LoginRequest loginRequest) {
    String username = loginRequest.getUsername();
    if (isLockedOut(username)) {
      log.warn("to many failed login attempts");
      return ResponseEntity.status(429).body("Too many failed login attempts. Try again later.");
    }
    Optional<User> userOptional = userRepository.findByUsername(loginRequest.getUsername());
    if (userOptional.isPresent()) {
      User user = userOptional.get();
      if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
        log.info("user logged in");
        loginAttempts.remove(username);
        return ResponseEntity.ok().body(new LoginResponse(this.jwtService.generateToken(user.getUsername())));
      } else {
        log.warn("password doesnt match with username");
        recordFailedAttempt(username);
        return ResponseEntity.status(409).body("Password is incorrect");
      }
    } else {
      log.warn("username not found: {}", loginRequest.getUsername());
      recordFailedAttempt(username);
      return ResponseEntity.status(409).body("username was not found");
    }
  }

  private void recordFailedAttempt(String username) {
    if (!loginAttempts.containsKey(username)) {
      loginAttempts.put(username, new FailedLoginAttempt(1, System.currentTimeMillis()));
    } else {
      FailedLoginAttempt attempt = loginAttempts.get(username);
      attempt.incrementAttempts();
      loginAttempts.put(username, attempt);
    }
  }

  private boolean isLockedOut(String username) {
    if (!loginAttempts.containsKey(username)) {
      return false;
    }

    FailedLoginAttempt attempt = loginAttempts.get(username);
    if (attempt.getAttempts() >= MAX_ATTEMPTS) {
      long lockoutTime = attempt.getFirstAttemptTime() + LOCKOUT_DURATION;
      if (System.currentTimeMillis() < lockoutTime) {
        return true;
      } else {
        loginAttempts.remove(username);
        return false;
      }
    }

    return false;
  }
}
