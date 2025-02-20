package ru.practicum.ewm.user.mapper;

import org.mapstruct.Mapper;
import ru.practicum.ewm.user.dto.NewUserRequest;
import ru.practicum.ewm.user.dto.UserDto;
import ru.practicum.ewm.user.model.User;
import ru.practicum.ewm.user.dto.UserShortDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserShortDto map(User user);

    User mapToUser(NewUserRequest request);

    UserDto mapToUserDto(User user);

    List<UserDto> mapToUsersDto(List<User> users);
}
