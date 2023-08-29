package ru.clevertec.userservice.user.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.clevertec.userservice.util.Role;
import ru.clevertec.userservice.util.validation.OnUpdate;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role userRole;
}
