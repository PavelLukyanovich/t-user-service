package ru.clevertec.userservice.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.userservice.user.model.dto.UserDto;
import ru.clevertec.userservice.user.model.request.CreateUserRequest;
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

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody CreateUserRequest request) {
        log.info("###POST createUserRequest = {}", request);
        UserDto userDto = userService.saveUser(request);
        log.info("userDto = {}", userDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        log.info("###GET email = {}", email);
        UserDto userDto = userService.getUserByEmail(email);
        log.info("-------userDto = {}", userDto);
        return new ResponseEntity<>(HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        log.info("###GET getAllUsers");
        userService.getAllUsers();
        return new ResponseEntity<>(HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable UUID id) {
        log.info("###GET fetched id = {}", id);
        userService.getUserById(id);
        return new ResponseEntity<>(HttpStatus.FOUND);
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UpdateUserRequest request, @RequestParam UUID roleId) {
        log.info("###PUT updateUserRequest = {}", request);
        userService.updateUser(request, roleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable UUID id) {
        log.info("###DELETE fetched id = {}", id);
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
