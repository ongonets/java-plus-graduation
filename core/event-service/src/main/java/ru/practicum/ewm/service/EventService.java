package ru.practicum.ewm.service;

import ru.practicum.ewm.dto.*;

import java.util.Collection;

public interface EventService {
    Collection<EventShortDto> findBy(PrivateSearchEventDto privateSearchEventDto);

    EventFullDto findBy(ParamEventDto paramEventDto, String ip);

    Collection<EventFullDto> findEventsAdmin(AdminSearchEventDto adminSearchEventDto);

    EventFullDto findEventByIdPublic(long id, String ip);

    Collection<EventShortDto> findEventsPublic(PublicSearchEventParams params);

    EventFullDto create(long userId, NewEventDto newEvent);

    EventFullDto update(ParamEventDto paramEventDto, UpdateEventUserRequest updateEvent);

    EventFullDto update(long eventId, UpdateEventUserRequest updateEvent);
}
