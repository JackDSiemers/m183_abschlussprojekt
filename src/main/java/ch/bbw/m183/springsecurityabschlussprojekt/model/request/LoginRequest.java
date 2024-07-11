package ch.bbw.m183.springsecurityabschlussprojekt.model.request;

import lombok.Data;

@Data
public class LoginRequest {

  private String username;
  private String password;
}
