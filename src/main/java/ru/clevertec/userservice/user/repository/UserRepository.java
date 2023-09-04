package ru.clevertec.userservice.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.clevertec.userservice.user.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, java.util.UUID> {
    User findUserByEmail(String email);
}
