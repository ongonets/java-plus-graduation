package ru.practicum.ewm.service;

import ru.practicum.ewm.dto.*;

import java.util.List;

public interface RequestService {
    List<ParticipationRequestDto> findRequest(long userId);

    ParticipationRequestDto createRequest(long userId, long eventId);

    ParticipationRequestDto cancelRequest(long userId, long requestId);

    List<ParticipationRequestDto> findRequest(ParamEventDto paramEventDto);

    EventRequestStatusUpdateResult updateRequest(ParamEventDto paramEventDto,
                                                 EventRequestStatusUpdateRequest updateRequest);

    List<RequestCountDto> findConfirmedRequest(List<Long> ids);
}
