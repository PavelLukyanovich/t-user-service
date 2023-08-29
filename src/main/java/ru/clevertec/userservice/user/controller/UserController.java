package ru.clevertec.userservice.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.userservice.user.model.dto.UserDto;
import ru.clevertec.userservice.user.model.request.CreateUserRequest;
import ru.clevertec.userservice.user.model.request.UpdateUserRequest;
import ru.clevertec.userservice.user.model.response.ApiResponse;
import ru.clevertec.userservice.user.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ApiResponse<UserDto> createUser(@RequestBody CreateUserRequest request) {

        log.info("###POST createUserRequest = {}", request);

        UserDto userDto = userService.saveUser(request);
        log.info("userDto = {}", userDto);
        return new ApiResponse<>(userDto);
    }

    @GetMapping("/email/{email}")
    public ApiResponse<UserDto> getUserByEmail(@PathVariable String email) {
        log.info("###GET email = {}", email);
        UserDto userDto = userService.getUserByEmail(email);
        log.info("-------userDto = {}", userDto);
        return new ApiResponse<>(userDto);
    }

    @GetMapping
    public ApiResponse<List<UserDto>> getAllUsers() {

        log.info("###GET getAllUsers");

        return new ApiResponse<>(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ApiResponse<UserDto> getUserById(@PathVariable UUID id) {

        log.info("###GET fetched id = {}", id);

        return new ApiResponse<>(userService.getUserById(id));
    }

    @PutMapping
    public ApiResponse<UserDto> updateUser(@RequestBody UpdateUserRequest request) {

        log.info("###PUT updateUserRequest = {}", request);

        return new ApiResponse<>(userService.updateUser(request));

    }

    @DeleteMapping("/{id}")
    public ApiResponse<UserDto> deleteUser(@PathVariable UUID id) {

        log.info("###DELETE fetched id = {}", id);

        return new ApiResponse<>(userService.deleteUser(id));
    }

}
