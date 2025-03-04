package ru.practicum.ewm.controller;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.*;
import ru.practicum.ewm.service.RequestService;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
@Slf4j
@Validated
public class PrivateRequestController implements RequestClient {

    private final RequestService requestService;

    @GetMapping("/{userId}/events/{eventId}/requests")
    public List<ParticipationRequestDto> findEventRequest(@Positive @PathVariable long userId,
                                                          @Positive @PathVariable long eventId) {
        ParamEventDto paramEventDto = new ParamEventDto(userId, eventId);
        log.info("Request to find eventRequests {}", paramEventDto);
        return requestService.findRequest(paramEventDto);
    }

    @PatchMapping("/{userId}/events/{eventId}/requests")
    public EventRequestStatusUpdateResult updateEventRequest(@Positive @PathVariable long userId,
                                                             @Positive @PathVariable long eventId,
                                                             @RequestBody EventRequestStatusUpdateRequest updateEvent) {
        ParamEventDto paramEventDto = new ParamEventDto(userId, eventId);
        log.info("Request to update eventRequests {}", paramEventDto);
        return requestService.updateRequest(paramEventDto, updateEvent);
    }

    @Override
    public List<RequestCountDto> findConfirmedRequest(List<Long> ids) {
        return requestService.findConfirmedRequest(ids);
    }
}
