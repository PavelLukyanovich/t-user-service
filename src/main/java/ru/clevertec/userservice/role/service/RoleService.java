package ru.clevertec.userservice.role.service;

import ru.clevertec.userservice.role.domain.Role;
import ru.clevertec.userservice.role.model.CreateRoleRequest;

import java.util.UUID;

public interface RoleService {

    Role getRole(UUID roleId);

    Role createRole(CreateRoleRequest request);
}
