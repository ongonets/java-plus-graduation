package ru.practicum.ewm.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.ewm.dto.EventRequestStatusUpdateResult;
import ru.practicum.ewm.dto.ParticipationRequestDto;
import ru.practicum.ewm.model.Request;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RequestMapper {

    @Mapping(expression = "java(request.getUserId())", target = "requester")
    @Mapping(expression = "java(request.getEvent().getId())", target = "event")
    ParticipationRequestDto mapToDto(Request request);

    @Mapping(source = "confirmedRequests", target = "confirmedRequests")
    @Mapping(source = "rejectedRequests", target = "rejectedRequests")
    EventRequestStatusUpdateResult mapToRequestStatus(List<Request> confirmedRequests, List<Request> rejectedRequests);

    List<ParticipationRequestDto> mapToDto(List<Request> requests);


}
