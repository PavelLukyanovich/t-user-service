package ru.clevertec.userservice.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.clevertec.userservice.user.domain.User;
import ru.clevertec.userservice.user.model.dto.UserDto;
import ru.clevertec.userservice.user.model.request.CreateUserRequest;
import ru.clevertec.userservice.user.model.request.UpdateUserRequest;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface UserMapper {

    UserDto userToUserDto(User User);

    User userDtoToUser(UserDto userDto);
    CreateUserRequest userToUserRequest(User User);
    UpdateUserRequest userToUpdateRequest(User User);


    User requestToUser(CreateUserRequest userRequest);
}
