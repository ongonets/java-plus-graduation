package ru.practicum.ewm.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class EventFullDto {
    String annotation;
    CategoryDto category;
    long confirmedRequests;
    LocalDateTime createdOn;
    String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate;
    long id;
    UserShortDto initiator;
    Location location;
    long participantLimit;
    LocalDateTime publishedOn;
    boolean requestModeration;
    EventState state;
    boolean paid;
    String title;
    long views;
}
