package ch.bbw.m183.springsecurityabschlussprojekt.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
  private String jwtToken;
}
