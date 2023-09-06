package ru.clevertec.userservice.security.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.clevertec.userservice.exception.appException.BadLoginParamException;
import ru.clevertec.userservice.security.jwt.JwtTokenProvider;
import ru.clevertec.userservice.security.model.request.JwtRequest;
import ru.clevertec.userservice.security.model.response.JwtResponse;
import ru.clevertec.userservice.security.service.AuthenticationService;
import ru.clevertec.userservice.user.domain.User;
import ru.clevertec.userservice.user.mapper.UserMapper;
import ru.clevertec.userservice.user.model.request.UpdateUserRequest;
import ru.clevertec.userservice.user.service.UserService;
import ru.clevertec.userservice.usertoken.domain.VerificationToken;
import ru.clevertec.userservice.usertoken.service.UserTokenService;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final UserMapper userMapper;
    private final UserTokenService userTokenService;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public JwtResponse login(JwtRequest loginRequest) {
        JwtResponse jwtResponse = new JwtResponse();
        log.info("__________________________1---");
        log.info("#POST token {}", loginRequest);
        if (checkLoginParam(loginRequest)) {
            throw new BadLoginParamException("Login parameters are not satisfy");
        }
        var authentication = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authentication);
        final String token = jwtTokenProvider.createAccessToken(authenticate);
        jwtResponse.setEmail(loginRequest.getEmail());
        jwtResponse.setAccessToken(token);
        return jwtResponse;
    }

    @Override
    public JwtResponse confirm(String token) {
        JwtResponse jwtResponse = new JwtResponse();
        VerificationToken verificationToken = userTokenService.getVerificationToken(token);
        User user = userMapper.userDtoToUser(userService.getUserById(verificationToken.getUser().getUserId()));
        UpdateUserRequest updateUserRequest = userMapper.userToUpdateRequest(user);
        updateUserRequest.setActive(true);
        user.setActive(true);
        userService.updateUser(updateUserRequest);
        final String confirmToken = jwtTokenProvider.createAccessToken(user);
        jwtResponse.setEmail(user.getEmail());
        jwtResponse.setAccessToken(confirmToken);
        return jwtResponse;
    }

    private boolean checkLoginParam(JwtRequest loginRequest) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        var userByEmail = userService.getUserByEmail(loginRequest.getEmail());
        var encodedPassword = userByEmail.password();
        var isPasswordsMatch = bCryptPasswordEncoder.matches(loginRequest.getPassword(), encodedPassword);
        return userByEmail.email().equals(loginRequest.getEmail())
                && isPasswordsMatch
                && !userByEmail.isActive();
    }
}
