package ru.clevertec.userservice.user.model.dto;

import ru.clevertec.userservice.role.domain.Role;

import java.util.UUID;

public record JournalistDTO(UUID userId,
                            String firstName,
                            String lastName,
                            String email,
                            String password,
                            String phone,
                            String address,
                            boolean isActivated,
                            Role role) {
}
