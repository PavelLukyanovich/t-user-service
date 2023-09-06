package ru.clevertec.userservice.security.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.clevertec.userservice.user.model.request.CreateJournalistRequest;

@Data
@EqualsAndHashCode(callSuper = true)
public class RegisterJournalistRequest extends CreateJournalistRequest {
    @NotNull(message = "address field can't be blank")
    private String address;
    @NotNull(message = "phone can't be blank")
    private String phone;

    public void setPassword(String password) {
        super.setPassword(new BCryptPasswordEncoder().encode(password));
    }
}
