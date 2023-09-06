package ru.clevertec.userservice.link.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.userservice.link.domain.ActivationLink;

import java.util.UUID;

public interface LinkRepository extends JpaRepository<ActivationLink, UUID> {
}
