package ru.clevertec.userservice.security.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.clevertec.userservice.user.mapper.UserMapper;
import ru.clevertec.userservice.user.service.UserService;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return Optional.ofNullable(userService.getUserByEmail(email))
                .map(userMapper::userDtoToUser)
                .map(JwtEntityFactory::create)
                .orElseThrow(() -> new BadCredentialsException(email));
    }

}
