package ru.practicum.evm.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.evm.mapper.AnalyzerMapper;
import ru.practicum.evm.model.UserAction;
import ru.practicum.evm.repository.UserActionRepository;
import ru.practicum.ewm.stats.avro.UserActionAvro;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserActionHandler {

    private final AnalyzerMapper mapper;
    private final UserActionRepository repository;

    public void handle(UserActionAvro userActionAvro) {
        UserAction userAction = mapper.map(userActionAvro);
        repository.save(userAction);
        log.info("Saved user ID = {} weight: {}", userAction.getUserId(),userAction.getWeight());
    }
}
