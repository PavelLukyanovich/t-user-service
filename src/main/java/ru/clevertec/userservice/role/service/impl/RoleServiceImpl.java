package ru.clevertec.userservice.role.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.userservice.role.domain.Role;
import ru.clevertec.userservice.role.model.CreateRoleRequest;
import ru.clevertec.userservice.role.repository.RoleRepository;
import ru.clevertec.userservice.role.service.RoleService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getRole(UUID roleId) {
        return roleRepository.findRoleByRoleId(roleId);
    }

    @Override
    public Role createRole(CreateRoleRequest request) {
        Role role = new Role();
        role.setRoleName(request.getRoleName().getRoleName());
        role.setRoleType(request.getRoleType());
        return roleRepository.save(role);
    }
}
