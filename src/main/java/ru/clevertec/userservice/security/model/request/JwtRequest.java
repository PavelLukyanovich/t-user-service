package ru.clevertec.userservice.security.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JwtRequest {
    @NotNull(message = "email must be not null")
    private String email;
    @NotNull(message = "password must be not null")
    private String password;
}
