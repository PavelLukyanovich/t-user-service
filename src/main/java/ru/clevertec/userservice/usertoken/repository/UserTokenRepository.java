package ru.clevertec.userservice.usertoken.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.clevertec.userservice.link.domain.ActivationLink;
import ru.clevertec.userservice.usertoken.domain.VerificationToken;

import java.util.UUID;

public interface UserTokenRepository extends JpaRepository<VerificationToken, UUID> {
    VerificationToken findTokenByToken(String token);
    @Query(value = "insert into links (link) values (:link)", nativeQuery = true)
    ActivationLink saveLink(@Param("link") ActivationLink link);
}
