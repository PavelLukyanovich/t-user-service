package ru.clevertec.userservice.role.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.userservice.role.domain.Role;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    Role findRoleByRoleId(UUID roleId);

    Role findRoleByRoleName(String roleName);
}
