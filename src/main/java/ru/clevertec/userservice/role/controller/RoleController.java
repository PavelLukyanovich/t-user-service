package ru.clevertec.userservice.role.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.userservice.role.domain.Role;
import ru.clevertec.userservice.role.model.request.CreateRoleRequest;
import ru.clevertec.userservice.role.model.response.RoleResponse;
import ru.clevertec.userservice.role.service.RoleService;

import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/api/v1/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public RoleResponse createRole(@RequestBody CreateRoleRequest request) {
        var role = roleService.createRole(request);
        return new RoleResponse(role, HttpStatus.CREATED);
    }

    @GetMapping("/{roleId}")
    public Role getRoleById(@PathVariable UUID roleId) {
        return roleService.getRole(roleId);
    }

}
