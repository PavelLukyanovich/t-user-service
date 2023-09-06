package ru.clevertec.userservice.role.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.userservice.role.domain.Role;
import ru.clevertec.userservice.role.model.request.CreateRoleRequest;
import ru.clevertec.userservice.role.repository.RoleRepository;
import ru.clevertec.userservice.role.service.RoleService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override

    public Role getRole(UUID roleId) {
        return roleRepository.findRoleByRoleId(roleId);
    }

    @Override
    @Transactional
    public Role createRole(CreateRoleRequest request) {
        Role role = new Role();
        role.setRoleName(request.getRoleName());
        return roleRepository.save(role);
    }
}
