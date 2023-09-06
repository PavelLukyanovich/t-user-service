package ru.clevertec.userservice.role.model.response;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import ru.clevertec.userservice.role.domain.Role;

public class RoleResponse extends ResponseEntity<Role> {

    public RoleResponse(Role role, HttpStatusCode status) {
        super(role, status);
    }
}
