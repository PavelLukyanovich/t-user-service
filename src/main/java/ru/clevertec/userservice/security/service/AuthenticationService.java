package ru.clevertec.userservice.security.service;

import ru.clevertec.userservice.security.model.request.JwtRequest;
import ru.clevertec.userservice.security.model.response.JwtResponse;

public interface AuthenticationService {

    JwtResponse login(JwtRequest loginRequest);

    JwtResponse confirm(String token);
}
