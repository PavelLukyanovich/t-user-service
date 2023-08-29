package ru.clevertec.userservice.security.jwt;

import ru.clevertec.userservice.security.model.dto.UserDetailsDto;
import ru.clevertec.userservice.user.domain.User;
import ru.clevertec.userservice.util.Role;

public class JwtEntityFactory {

    public static UserDetailsDto create(User user) {
        return new UserDetailsDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getRole())
        );
    }

    private static Role mapToGrantedAuthorities(Role role) {
        return role;
    }


}
