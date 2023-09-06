package ru.clevertec.userservice.security.model.response;

import lombok.Data;

@Data
public class JwtResponse {

    private String email;
    private String accessToken;

}
