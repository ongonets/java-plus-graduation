package ru.practicum.ewm.controller;

import feign.FeignException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.FindUsersParams;
import ru.practicum.ewm.dto.NewUserRequest;
import ru.practicum.ewm.dto.UserDto;
import ru.practicum.ewm.dto.UserShortDto;
import ru.practicum.ewm.service.UserService;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "admin/users")
public class UserController implements UserClient {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@Valid @RequestBody NewUserRequest request) {
        log.info("Received request to create new user: {}", request.getName());
        return userService.createUser(request);
    }

    @GetMapping
    public List<UserDto> findUsers(@RequestParam(required = false) List<Long> ids,
                                   @RequestParam(value = "from", defaultValue = "0") int from,
                                   @RequestParam(value = "size", defaultValue = "10") int size) {
        FindUsersParams params = new FindUsersParams(ids, from, size);
        log.info("Received request to find users with params: {}", params);
        return userService.findUsers(params);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable @Positive long id) {
        log.info("Received request to delete user with id: {}", id);
        userService.deleteUser(id);
    }

    @Override
    public List<UserShortDto> findShortUsers(List<Long> ids) throws FeignException {
        return userService.findShortUsers(ids);
    }
}
