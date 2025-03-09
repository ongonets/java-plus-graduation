package ru.yandex.practicum.handler;

import org.springframework.stereotype.Component;
import ru.practicum.ewm.stats.proto.InteractionsCountRequestProto;
import ru.practicum.ewm.stats.proto.RecommendedEventProto;
import ru.practicum.ewm.stats.proto.SimilarEventsRequestProto;
import ru.practicum.ewm.stats.proto.UserPredictionsRequestProto;

import java.util.stream.Stream;


@Component
public class RecommendationHandler {
    public Stream<RecommendedEventProto> handle(UserPredictionsRequestProto request) {
        return Stream.of(getEvent());

    }

    public Stream<RecommendedEventProto> handle(SimilarEventsRequestProto request) {
        return Stream.of(getEvent());
    }

    public Stream<RecommendedEventProto> handle(InteractionsCountRequestProto request) {
        return Stream.of(getEvent());
    }

    public RecommendedEventProto getEvent() {
        return RecommendedEventProto.newBuilder()
                .setEventId(1)
                .setScore(1)
                .build();
    }
}
