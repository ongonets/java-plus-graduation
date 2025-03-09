package ru.practicum.ewm.controller;

import feign.FeignException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.dto.EventFullDto;
import ru.practicum.ewm.dto.EventShortDto;
import ru.practicum.ewm.dto.EventWithInitiatorDto;
import ru.practicum.ewm.dto.PublicSearchEventParams;
import ru.practicum.ewm.model.Sorting;
import ru.practicum.ewm.service.EventService;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/events")
public class PublicEventController implements EventClient {
    private final EventService eventService;

    @GetMapping
    public Collection<EventShortDto> findEvents(@RequestParam(required = false) String text,
                                                @RequestParam(required = false) List<@Positive Long> categories,
                                                @RequestParam(required = false) Boolean paid,
                                                @RequestParam(required = false) LocalDateTime rangeStart,
                                                @RequestParam(required = false) LocalDateTime rangeEnd,
                                                @RequestParam(required = false) boolean onlyAvailable,
                                                @RequestParam(required = false) Sorting sort,
                                                @RequestParam(defaultValue = "0") int from,
                                                @RequestParam(defaultValue = "10") int size,
                                                HttpServletRequest request) {
        PublicSearchEventParams params = new PublicSearchEventParams(text, categories, paid, rangeStart, rangeEnd,
                onlyAvailable, sort, from, size, request.getRemoteAddr());
        log.info("Received public request to find events with params: {}", params);
        return eventService.findEventsPublic(params);
    }

    @GetMapping("/{id}")
    public EventFullDto findEventById(@Positive @PathVariable long id, HttpServletRequest request) {
        log.info("Received public request to find event with ID = {}", id);
        return eventService.findEventByIdPublic(id, request.getRemoteAddr());
    }

    @Override
    public EventWithInitiatorDto findEventWithInitiator(long eventId) throws FeignException {
        return eventService.findBy(eventId);
    }
}
