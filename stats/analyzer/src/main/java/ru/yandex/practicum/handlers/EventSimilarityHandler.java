package ru.yandex.practicum.handlers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.practicum.ewm.stats.avro.EventSimilarityAvro;

@Component
@RequiredArgsConstructor
@Slf4j
public class EventSimilarityHandler {

    public void handle(EventSimilarityAvro eventSimilarityAvro) {
        log.info("Handle similarity for events A = {} and B = {}",
                eventSimilarityAvro.getEventA(),
                eventSimilarityAvro.getEventB());
    }




}
