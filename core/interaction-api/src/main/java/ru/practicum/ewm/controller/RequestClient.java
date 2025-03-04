package ru.practicum.ewm.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.practicum.ewm.dto.RequestCountDto;

import java.util.List;

@FeignClient(name = "request-service", path = "/users")
public interface RequestClient {

    @GetMapping("/requests")
    List<RequestCountDto> findConfirmedRequest(@RequestParam List<Long> ids);
}
