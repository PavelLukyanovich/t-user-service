package ru.clevertec.userservice.user.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateUserRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private String address;
}
