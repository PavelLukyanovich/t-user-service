package ru.clevertec.userservice.security.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.userservice.security.model.request.JwtRequest;
import ru.clevertec.userservice.security.model.request.RegisterUserRequest;
import ru.clevertec.userservice.security.model.response.JwtResponse;
import ru.clevertec.userservice.security.service.AuthenticationService;
import ru.clevertec.userservice.user.domain.User;
import ru.clevertec.userservice.user.mapper.UserMapper;
import ru.clevertec.userservice.user.model.dto.UserDto;
import ru.clevertec.userservice.user.service.UserService;
import ru.clevertec.userservice.util.api.ResponseApi;

@RestController
@Slf4j
@RequestMapping("/api/v1/authentication")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/login")
    public JwtResponse login(@Validated @RequestBody JwtRequest emailRequest) {
        log.info("JwtResponse = {}", authenticationService.login(emailRequest));
        return authenticationService.login(emailRequest);
    }

    @PostMapping("/register")
    public ResponseApi<User> register(@Validated @RequestBody RegisterUserRequest request) {
        log.info("Request = {}", request);
        UserDto createdUser = userService.saveUser(request);
        User user = userMapper.userDtoToUser(createdUser);
        return new ResponseApi<>(user);
    }
}
