package ru.clevertec.userservice.user.service;

import org.springframework.http.ResponseEntity;
import ru.clevertec.userservice.user.model.dto.UserDto;
import ru.clevertec.userservice.user.model.request.CreateJournalistRequest;
import ru.clevertec.userservice.user.model.request.CreateUserRequest;
import ru.clevertec.userservice.user.model.request.UpdateJournalistRequest;
import ru.clevertec.userservice.user.model.request.UpdateUserRequest;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserDto getUserById(UUID id);

    List<UserDto> getAllUsers();

    String saveUser(CreateUserRequest request);

    String saveJournalist(CreateJournalistRequest request);

    ResponseEntity<UserDto> deleteUser(UUID id);

    UserDto updateUser(UpdateUserRequest request);
    UserDto updateJournalist(UpdateJournalistRequest request);

    UserDto getUserByEmail(String email);
}

