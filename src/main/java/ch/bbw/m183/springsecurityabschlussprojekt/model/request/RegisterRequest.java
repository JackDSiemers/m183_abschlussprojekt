package ch.bbw.m183.springsecurityabschlussprojekt.model.request;

import lombok.Data;

@Data
public class RegisterRequest {
  private String username;
  private String password;
  private String firstname;
  private String lastname;
}
