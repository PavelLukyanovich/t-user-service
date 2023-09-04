package ru.clevertec.userservice.user.service;

import ru.clevertec.userservice.user.model.dto.UserDto;
import ru.clevertec.userservice.user.model.request.CreateUserRequest;
import ru.clevertec.userservice.user.model.request.UpdateUserRequest;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserDto getUserById(UUID id);

    List<UserDto> getAllUsers();

    UserDto saveUser(CreateUserRequest request);

    UserDto deleteUser(UUID id);

    UserDto updateUser(UpdateUserRequest request, UUID roleId);

    UserDto getUserByEmail(String email);
}

