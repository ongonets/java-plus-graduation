package ru.yandex.practicum;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.ewm.stats.avro.EventSimilarityAvro;
import ru.practicum.ewm.stats.avro.UserActionAvro;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RecordHandler {


    public Optional<EventSimilarityAvro> updateState(UserActionAvro userAction) {
        EventSimilarityAvro eventSimilarityAvro = EventSimilarityAvro.newBuilder()
                .setEventA(userAction.getEventId())
                .setEventB(userAction.getEventId() + 1)
                .setTimestamp(userAction.getTimestamp())
                .setScore(1)
                .build();
        return Optional.of(eventSimilarityAvro);
    }




}
