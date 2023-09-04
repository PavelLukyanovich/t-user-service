package ru.clevertec.userservice.role.model;

import lombok.Data;
import ru.clevertec.userservice.role.domain.Role;
import ru.clevertec.userservice.role.domain.RoleType;

@Data
public class CreateRoleRequest {
    private Role roleName;
    private RoleType roleType;
}
