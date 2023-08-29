package ru.clevertec.userservice.user.model.dto;

import ru.clevertec.userservice.util.Role;

import java.util.UUID;

public record UserDto(UUID id, String firstName, String lastName, String email, String password, Role role) {
}
