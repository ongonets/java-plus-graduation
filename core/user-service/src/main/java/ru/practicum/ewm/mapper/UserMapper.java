package ru.practicum.ewm.mapper;

import org.mapstruct.Mapper;
import ru.practicum.ewm.dto.NewUserRequest;
import ru.practicum.ewm.dto.UserDto;
import ru.practicum.ewm.model.User;
import ru.practicum.ewm.dto.UserShortDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserShortDto map(User user);

    User mapToUser(NewUserRequest request);

    UserDto mapToUserDto(User user);

    List<UserDto> mapToUsersDto(List<User> users);

    List<UserShortDto> mapToUserShortDto(List<User> users);
}
