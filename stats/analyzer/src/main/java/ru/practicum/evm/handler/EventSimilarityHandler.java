package ru.practicum.evm.handler;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.practicum.evm.mapper.AnalyzerMapper;
import ru.practicum.evm.model.EventSimilarity;
import ru.practicum.evm.repository.EventSimilarityRepository;
import ru.practicum.ewm.stats.avro.EventSimilarityAvro;

@Component
@RequiredArgsConstructor
@Slf4j
public class EventSimilarityHandler {

    private final AnalyzerMapper mapper;
    private final EventSimilarityRepository repository;

    public void handle(EventSimilarityAvro eventSimilarityAvro) {
        EventSimilarity eventSimilarity = mapper.map(eventSimilarityAvro);
        repository.save(eventSimilarity);
        log.info("Saved similarity for events A = {} and B = {}",
                eventSimilarityAvro.getEventA(),
                eventSimilarityAvro.getEventB());
    }




}
