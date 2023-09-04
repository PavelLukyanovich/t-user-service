package ru.clevertec.userservice.role.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.clevertec.userservice.role.domain.Role;
import ru.clevertec.userservice.user.domain.User;

public interface RoleRepository extends JpaRepository<Role, java.util.UUID> {

    Role findRoleByRoleId(java.util.UUID roleId);

    Role findRoleByUser(User User);
}
