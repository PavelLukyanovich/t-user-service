package ru.clevertec.userservice.user.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean isActivated;
}
