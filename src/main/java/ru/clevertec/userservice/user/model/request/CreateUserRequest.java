package ru.clevertec.userservice.user.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.clevertec.userservice.role.domain.Role;

@Data
@NoArgsConstructor
public class CreateUserRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean isActivated;
    private Role role;
}
