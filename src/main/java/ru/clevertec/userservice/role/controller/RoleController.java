package ru.clevertec.userservice.role.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.userservice.role.domain.Role;
import ru.clevertec.userservice.role.model.CreateRoleRequest;
import ru.clevertec.userservice.role.service.RoleService;

import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/api/v1/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody CreateRoleRequest request) {
        roleService.createRole(request);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/{roleId}")
    public ResponseEntity<Role> getRoleById(@PathVariable UUID roleId) {
        log.info("###GET roleId = {}", roleId);
        Role role = roleService.getRole(roleId);
        log.info("-------role = {}", role);
        return new ResponseEntity<>(HttpStatus.FOUND);
    }

}
