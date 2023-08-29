package ru.clevertec.userservice.security.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.clevertec.userservice.security.jwt.JwtTokenProvider;
import ru.clevertec.userservice.security.model.request.JwtRequest;
import ru.clevertec.userservice.security.model.response.JwtResponse;
import ru.clevertec.userservice.security.service.AuthenticationService;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public JwtResponse login(JwtRequest loginRequest) {

        JwtResponse jwtResponse = new JwtResponse();
        log.info("__________________________1---");
        log.info("#POST token {}", loginRequest);
        var authentication = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authentication);

        final String token = jwtTokenProvider.createAccessToken(authenticate);
        jwtResponse.setEmail(loginRequest.getEmail());
        jwtResponse.setAccessToken(token);

        return jwtResponse;
    }
}
