package ru.practicum.ewm.service;

import ru.practicum.ewm.dto.*;

import java.util.Collection;

public interface EventService {
    Collection<EventShortDto> findBy(PrivateSearchEventDto privateSearchEventDto);

    EventFullDto findBy(ParamEventDto paramEventDto);

    Collection<EventFullDto> findEventsAdmin(AdminSearchEventDto adminSearchEventDto);

    EventFullDto findEventByIdPublic(long eventId, long userId);

    Collection<EventShortDto> findEventsPublic(PublicSearchEventParams params);

    EventFullDto create(long userId, NewEventDto newEvent);

    EventFullDto update(ParamEventDto paramEventDto, UpdateEventUserRequest updateEvent);

    EventFullDto update(long eventId, UpdateEventUserRequest updateEvent);

    EventWithInitiatorDto findBy(long eventId);

    Collection<EventShortDto> findRecommendations(long userId);

    void addLike(long eventId, long userId);
}
