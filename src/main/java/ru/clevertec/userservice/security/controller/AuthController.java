package ru.clevertec.userservice.security.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.userservice.security.model.request.JwtRequest;
import ru.clevertec.userservice.security.model.request.RegisterJournalistRequest;
import ru.clevertec.userservice.security.model.request.RegisterUserRequest;
import ru.clevertec.userservice.security.model.response.JwtResponse;
import ru.clevertec.userservice.security.service.AuthenticationService;
import ru.clevertec.userservice.user.service.UserService;

@RestController
@Slf4j
@RequestMapping("/api/v1/authentication")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    private final UserService userService;

    @PostMapping("/login")
    public JwtResponse login(@Validated @RequestBody JwtRequest emailRequest) {
        log.info("JwtResponse = {}", authenticationService.login(emailRequest));
        return authenticationService.login(emailRequest);
    }

    @PostMapping("/register/user")
    public String registerUser(@Validated @RequestBody RegisterUserRequest request) {
        log.info("Request = {}", request);
        return userService.saveUser(request);
    }

    @PostMapping("/register/journalist")
    public String registerJournalist(@Validated @RequestBody RegisterJournalistRequest request) {
        log.info("Request = {}", request);
        return userService.saveJournalist(request);
    }

    @GetMapping("/registrationConfirm")
    public JwtResponse confirmRegistration(@RequestParam("token") String token) {
        return authenticationService.confirm(token);
    }
}
