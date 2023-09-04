package ru.clevertec.userservice.user.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.clevertec.userservice.exception.appException.UserExistsException;
import ru.clevertec.userservice.exception.appException.UserNotFoundException;
import ru.clevertec.userservice.role.repository.RoleRepository;
import ru.clevertec.userservice.user.domain.User;
import ru.clevertec.userservice.user.mapper.UserMapper;
import ru.clevertec.userservice.user.model.dto.UserDto;
import ru.clevertec.userservice.user.model.request.CreateUserRequest;
import ru.clevertec.userservice.user.model.request.UpdateUserRequest;
import ru.clevertec.userservice.user.repository.UserRepository;
import ru.clevertec.userservice.user.service.UserService;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto getUserById(java.util.UUID id) {

        log.info("--getUserById fetched id = {}", id);
        UserDto userDto = userMapper.userToUserDto(userRepository.getReferenceById(id));
        log.info("--got user = {} ", userDto);
        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<UserDto> userDtos = userRepository.findAll().stream()
                .map(userMapper::userToUserDto)
                .toList();
        log.info("--got users list = {} ", userDtos);
        return userDtos;
    }

    @Override
    public UserDto saveUser(CreateUserRequest request) {
        log.info("User name from request {}", request.getFirstName());
        User user = userMapper.requestToUser(request);
        log.info("User role after mapping {}", user.getRole());
        User UserByEmail = userRepository.findUserByEmail(request.getEmail());
        if (Objects.nonNull(UserByEmail)) {
            log.info("UserExist exception user = {}", user);
            throw new UserExistsException("User already exist");
        }
        User savedUser = userRepository.save(user);
        log.info("SavedUser = {}", savedUser);

        return userMapper.userToUserDto(savedUser);

    }

    @Override
    public UserDto deleteUser(java.util.UUID id) {

        log.info("--deleteUser fetched id = {}", id);
        User byId = getUserIfExist(id);
        log.info("--userById = {}", byId);
        userRepository.deleteById(id);

        return userMapper.userToUserDto(byId);
    }

    @Override
    public UserDto updateUser(UpdateUserRequest request, java.util.UUID roleId) {

        User byId = getUserIfExist(request.getUserId());
        log.info("--fetched user = {}", byId);
        byId.setFirstName(request.getFirstName());
        byId.setLastName(request.getLastName());
        byId.setEmail(request.getEmail());
        byId.setPassword(request.getPassword());
        byId.setActivated(request.isActivated());
        byId.setRole(roleRepository.findRoleByRoleId(roleId));
        log.info("--user after update = {}", byId);

        return userMapper.userToUserDto(userRepository.save(byId));
    }

    @Override
    public UserDto getUserByEmail(String email) {

        log.info("--user email = {}", email);
        User UserByEmail = userRepository.findUserByEmail(email);
        log.info("--user = {}", UserByEmail);
        return userMapper.userToUserDto(UserByEmail);
    }

    private User getUserIfExist(java.util.UUID id) {

        log.info("--user id = {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
