package ch.bbw.m183.springsecurityabschlussprojekt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FailedLoginAttempt {
  private int attempts;
  private long firstAttemptTime;

  public void incrementAttempts() {
    this.attempts++;
  }
}
