package ru.clevertec.userservice.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.clevertec.userservice.user.domain.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, java.util.UUID> {
    Optional<User> findUserByEmail(String email);
}
