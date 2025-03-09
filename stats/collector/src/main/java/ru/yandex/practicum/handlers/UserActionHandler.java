package ru.yandex.practicum.handlers;

import ru.practicum.ewm.stats.proto.UserActionProto;

public interface UserActionHandler {

    void handle(UserActionProto event);
}