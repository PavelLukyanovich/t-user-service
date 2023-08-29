package ru.clevertec.userservice.user.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.clevertec.userservice.util.Role;

import java.util.UUID;

@Data
@NoArgsConstructor
public class CreateUserRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
}
