package ru.clevertec.userservice.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.userservice.user.model.dto.UserDto;
import ru.clevertec.userservice.user.model.request.UpdateUserRequest;
import ru.clevertec.userservice.user.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/api/v1/admin/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/email/{email}")
    public UserDto getUserByEmail(@PathVariable String email) {
        log.info("###GET email = {}", email);
        UserDto userDto = userService.getUserByEmail(email);
        log.info("-------userDto = {}", userDto);
        return userDto;
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        log.info("###GET getAllUsers");
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable UUID id) {
        log.info("###GET fetched id = {}", id);
        return userService.getUserById(id);
    }

    @PutMapping
    public UserDto updateUser(@RequestBody UpdateUserRequest request) {
        log.info("###PUT updateUserRequest = {}", request);
        userService.updateUser(request);
        return userService.updateUser(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable UUID id) {
        log.info("###DELETE fetched id = {}", id);
        return userService.deleteUser(id);
    }
}
