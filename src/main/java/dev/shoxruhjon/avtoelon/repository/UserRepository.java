package dev.shoxruhjon.avtoelon.repository;

import dev.shoxruhjon.avtoelon.entity.UserEntity;
import dev.shoxruhjon.avtoelon.entity.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findFirstByEmail(String email);

    Optional<UserEntity> findByRole(Role role);
}