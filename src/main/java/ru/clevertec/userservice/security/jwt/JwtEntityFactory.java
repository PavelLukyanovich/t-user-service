package ru.clevertec.userservice.security.jwt;

import ru.clevertec.userservice.role.domain.Role;
import ru.clevertec.userservice.security.model.dto.UserDetailsDto;
import ru.clevertec.userservice.user.domain.User;

public class JwtEntityFactory {

    public static UserDetailsDto create(User User) {
        return new UserDetailsDto(
                User.getUserId(),
                User.getFirstName(),
                User.getLastName(),
                User.getEmail(),
                User.getPassword(),
               mapToGrantedAuthorities(User.getRole())
        );
    }

    private static Role mapToGrantedAuthorities(Role role) {
        return role;
    }


}
