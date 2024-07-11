package ch.bbw.m183.springsecurityabschlussprojekt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.bbw.m183.springsecurityabschlussprojekt.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String email);
  boolean existsByUsername(String email);
}
