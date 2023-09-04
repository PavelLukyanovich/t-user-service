package ru.clevertec.userservice.security.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.userservice.link.domain.ActivationLink;
import ru.clevertec.userservice.link.service.LinkService;
import ru.clevertec.userservice.security.model.request.JwtRequest;
import ru.clevertec.userservice.security.model.request.RegisterUserRequest;
import ru.clevertec.userservice.security.model.response.JwtResponse;
import ru.clevertec.userservice.security.service.AuthenticationService;
import ru.clevertec.userservice.user.domain.User;
import ru.clevertec.userservice.user.mapper.UserMapper;
import ru.clevertec.userservice.user.model.dto.UserDto;
import ru.clevertec.userservice.user.model.request.UpdateUserRequest;
import ru.clevertec.userservice.user.service.UserService;
import ru.clevertec.userservice.usertoken.domain.VerificationToken;
import ru.clevertec.userservice.usertoken.service.UserTokenService;

import java.time.LocalDateTime;

@RestController
@Slf4j
@RequestMapping("/api/v1/authentication")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    private final UserTokenService userTokenService;
    private final UserService userService;
    private final LinkService linkService;
    private final UserMapper userMapper;

    @PostMapping("/login")
    public JwtResponse login(@Validated @RequestBody JwtRequest emailRequest) {
        log.info("JwtResponse = {}", authenticationService.login(emailRequest));
        return authenticationService.login(emailRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<ActivationLink> register(@Validated @RequestBody RegisterUserRequest request) {
        log.info("Request = {}", request);
        UserDto createdUser = userService.saveUser(request);
        var verificationToken = userTokenService.generateVerificationToken(createdUser.userId());
        userTokenService.saveVerificationToken(verificationToken);
        var activationLink = linkService.generateLink(verificationToken);
        linkService.saveLink(activationLink);
        return new ResponseEntity<ActivationLink>(activationLink, HttpStatus.OK);
    }

    @GetMapping("/registrationConfirm")
    public ResponseEntity<ActivationLink> confirmRegistration(@RequestParam("token") String token) {

        VerificationToken verificationToken = userTokenService.getVerificationToken(token);

        User user =  userMapper.userDtoToUser(userService.getUserById(verificationToken.getUser().getUserId()));
        UpdateUserRequest updateUserRequest = userMapper.userToUpdateRequest(user);
        if ((verificationToken.getExpiryDate().isBefore(LocalDateTime.now()))) {
            return new ResponseEntity<>(HttpStatus.LOCKED);
        }
        updateUserRequest.setActivated(true);
        userService.updateUser(updateUserRequest, user.getRole().getRoleId() );
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
