package ru.clevertec.userservice.user.model.dto;


import ru.clevertec.userservice.role.domain.Role;

import java.util.UUID;

public record UserDto(UUID userId,
                      String firstName,
                      String lastName,
                      String email,
                      String password,
                      boolean isActive,
                      Role role) {
}
