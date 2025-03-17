package ru.practicum.ewm.controller;

import jakarta.validation.constraints.Positive;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.RequestCountDto;

import java.util.List;

@FeignClient(name = "request-service", path = "/users")
public interface RequestClient {

    @GetMapping("/requests")
    List<RequestCountDto> findConfirmedRequest(@RequestParam List<Long> ids);

    @GetMapping("/requests/confirmed")
    boolean isUserParticipatedInEvent(@Positive @RequestParam long userId, @Positive @RequestParam long eventId);
}
