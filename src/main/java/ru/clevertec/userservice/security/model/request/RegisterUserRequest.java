package ru.clevertec.userservice.security.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.clevertec.userservice.user.model.request.CreateUserRequest;

@Data
@EqualsAndHashCode(callSuper = true)
public class RegisterUserRequest extends CreateUserRequest {

    public void setPassword(String password) {
        super.setPassword(new BCryptPasswordEncoder().encode(password));
    }
}
