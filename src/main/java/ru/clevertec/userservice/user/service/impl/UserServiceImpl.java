package ru.clevertec.userservice.user.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.clevertec.userservice.exception.UserExistsException;
import ru.clevertec.userservice.exception.UserNotFoundException;
import ru.clevertec.userservice.user.model.dto.UserDto;
import ru.clevertec.userservice.user.domain.User;
import ru.clevertec.userservice.user.model.request.CreateUserRequest;
import ru.clevertec.userservice.user.model.request.UpdateUserRequest;
import ru.clevertec.userservice.user.repository.UserRepository;
import ru.clevertec.userservice.user.service.UserService;
import ru.clevertec.userservice.user.mapper.UserMapper;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto getUserById(UUID id) {

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

        User user = userMapper.requestToUser(request);

        User userByEmail = userRepository.findUserByEmail(request.getEmail());
        if (Objects.nonNull(userByEmail)) {
            log.info("UserExist exception user = {}", user);
            throw new UserExistsException("User already exist");

        }
        User savedUser = userRepository.save(user);
        log.info("SavedUser = {}", savedUser);

        return userMapper.userToUserDto(savedUser);

    }

    @Override
    public UserDto deleteUser(UUID id) {

        log.info("--deleteUser fetched id = {}", id);
        User byId = getUserIfExist(id);
        log.info("--userById = {}", byId);
        userRepository.deleteById(id);

        return userMapper.userToUserDto(byId);
    }

    @Override
    public UserDto updateUser(UpdateUserRequest request) {

        User byId = getUserIfExist(request.getId());
        log.info("--fetched user = {}", byId);
        byId.setFirstName(request.getFirstName());
        byId.setLastName(request.getLastName());
        byId.setEmail(request.getEmail());
        byId.setPassword(request.getPassword());
        byId.setRole(request.getUserRole());
        log.info("--user after update = {}", byId);

        return userMapper.userToUserDto(userRepository.save(byId));
    }

    @Override
    public UserDto getUserByEmail(String email) {

        log.info("--user email = {}", email);
        User userByEmail = userRepository.findUserByEmail(email);
        log.info("--user = {}", userByEmail);
        return userMapper.userToUserDto(userByEmail);
    }

    private User getUserIfExist(UUID id) {

        log.info("--user id = {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
