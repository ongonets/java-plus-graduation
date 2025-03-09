package ru.yandex.practicum.handlers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.stats.avro.UserActionAvro;

@Component
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserActionHandler {

    public void handle(UserActionAvro userAction) {
       log.info("Handle action {}", userAction);
    }
}
