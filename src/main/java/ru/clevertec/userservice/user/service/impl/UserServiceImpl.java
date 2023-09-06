package ru.clevertec.userservice.user.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.clevertec.userservice.exception.appException.UserExistsException;
import ru.clevertec.userservice.exception.appException.UserNotFoundException;
import ru.clevertec.userservice.link.domain.ActivationLink;
import ru.clevertec.userservice.link.service.LinkService;
import ru.clevertec.userservice.role.domain.UserRole;
import ru.clevertec.userservice.role.repository.RoleRepository;
import ru.clevertec.userservice.user.domain.User;
import ru.clevertec.userservice.user.mapper.UserMapper;
import ru.clevertec.userservice.user.model.dto.UserDto;
import ru.clevertec.userservice.user.model.request.CreateJournalistRequest;
import ru.clevertec.userservice.user.model.request.CreateUserRequest;
import ru.clevertec.userservice.user.model.request.UpdateJournalistRequest;
import ru.clevertec.userservice.user.model.request.UpdateUserRequest;
import ru.clevertec.userservice.user.repository.UserRepository;
import ru.clevertec.userservice.user.service.UserService;
import ru.clevertec.userservice.usertoken.service.UserTokenService;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final LinkService linkService;
    private final UserTokenService userTokenService;
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
    @Transactional
    public String saveUser(CreateUserRequest request) {
        User user = getUser(request.getEmail(), request.getFirstName(), userMapper.requestToUser(request));
        user.setRole(roleRepository.findRoleByRoleName(UserRole.USER.name()));
        ActivationLink activationLink = getActivationLink(user);
        userRepository.save(user);
        return activationLink.getLink();

    }

    @Override
    @Transactional
    public String saveJournalist(CreateJournalistRequest request) {
        User user = getUser(request.getEmail(), request.getFirstName(), userMapper.requestToUser(request));
        user.setRole(roleRepository.findRoleByRoleName(UserRole.JOURNALIST.name()));
        ActivationLink activationLink = getActivationLink(user);
        userRepository.save(user);
        return activationLink.getLink();
    }

    @Override
    public ResponseEntity<UserDto> deleteUser(UUID id) {

        log.info("--deleteUser fetched id = {}", id);
        User byId = getUserIfExist(id);
        log.info("--userById = {}", byId);
        userRepository.deleteById(id);

        return new ResponseEntity<>(userMapper.userToUserDto(byId), HttpStatus.NO_CONTENT);
    }

    @Override
    @Transactional
    public UserDto updateUser(UpdateUserRequest request) {

        User byId = getUserIfExist(request.getUserId());
        log.info("--fetched user = {}", byId);
        byId.setFirstName(request.getFirstName());
        byId.setLastName(request.getLastName());
        byId.setEmail(request.getEmail());
        byId.setPassword(request.getPassword());
        byId.setActive(request.isActive());
        log.info("--user after update = {}", byId);
        var save = userRepository.save(byId);

        return userMapper.userToUserDto(save);
    }

    @Override
    public UserDto updateJournalist(UpdateJournalistRequest request) {
        User byId = getUserIfExist(request.getUserId());
        log.info("--fetched user = {}", byId);
        byId.setFirstName(request.getFirstName());
        byId.setLastName(request.getLastName());
        byId.setEmail(request.getEmail());
        byId.setPassword(request.getPassword());
        byId.setPhone(request.getPhone());
        byId.setAddress(request.getAddress());
        byId.setActive(request.isActive());
        log.info("--user after update = {}", byId);
        var save = userRepository.save(byId);

        return userMapper.userToUserDto(save);
    }

    @Override
    public UserDto getUserByEmail(String email) {

        log.info("--user email = {}", email);
        User UserByEmail = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        log.info("--user = {}", UserByEmail);
        return userMapper.userToUserDto(UserByEmail);
    }

    private User getUser(String email, String firstName, User mappedUser) {
        userRepository.findUserByEmail(email).ifPresent(user -> {
            throw new UserExistsException("user already exist");
        });
        log.info("User name from request {}", firstName);
        log.info("User role after mapping {}", mappedUser.getRole());
        User savedUser = userRepository.save(mappedUser);
        log.info("SavedUser = {}", savedUser);
        return mappedUser;
    }

    private User getUserIfExist(java.util.UUID id) {

        log.info("--user id = {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    private ActivationLink getActivationLink(User user) {
        var verificationToken = userTokenService.generateVerificationToken(user.getUserId());
        userTokenService.saveVerificationToken(verificationToken);
        var activationLink = linkService.generateLink(verificationToken);
        linkService.saveLink(activationLink);
        return activationLink;
    }
}
