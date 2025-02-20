package ru.practicum.ewm.event.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import ru.practicum.ewm.event.model.EventState;

import java.time.LocalDateTime;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class AdminSearchEventDto {
    List<Long> users;
    List<EventState> states;
    List<Long> categories;
    LocalDateTime rangeStart;
    LocalDateTime rangeEnd;
    int from;
    int size;
}
