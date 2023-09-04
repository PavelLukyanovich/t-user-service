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
import ru.clevertec.userservice.user.service.UserService;

import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    @Override
    public JwtResponse login(JwtRequest loginRequest) {
        JwtResponse jwtResponse = new JwtResponse();
            log.info("__________________________1---");
            log.info("#POST token {}", loginRequest);

        if (checkLoginParam(loginRequest)) {
            var authentication = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
            Authentication authenticate = authenticationManager.authenticate(authentication);
            final String token = jwtTokenProvider.createAccessToken(authenticate);
            jwtResponse.setEmail(loginRequest.getEmail());
            jwtResponse.setAccessToken(token);
            return jwtResponse;
        }else {
            throw new BadLoginParamException("Login parameters are not satisfy");
        }
    }

    private boolean checkLoginParam(JwtRequest loginRequest) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        var encodedPassword = userService.getUserByEmail(loginRequest.getEmail()).password();
        var isPasswordsMatch = bCryptPasswordEncoder.matches(loginRequest.getPassword(), encodedPassword);
        return userService.getUserByEmail(loginRequest.getEmail()).email().equals(loginRequest.getEmail())
                && isPasswordsMatch
                && !userService.getUserByEmail(loginRequest.getEmail()).isActivated();
    }


}
