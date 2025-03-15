package ru.practicum.evm.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.evm.dto.RecommendedEvent;
import ru.practicum.evm.model.EventSimilarity;
import ru.practicum.evm.model.UserAction;
import ru.practicum.ewm.stats.avro.EventSimilarityAvro;
import ru.practicum.ewm.stats.avro.UserActionAvro;
import ru.practicum.ewm.stats.proto.RecommendedEventProto;

@Mapper(componentModel = "spring")
public interface AnalyzerMapper {



    @Mapping(target = "id", ignore = true)
    @Mapping(target = "weight", expression = "java(WeightMapper.mapToWeight(avro.getActionType()))")
    UserAction map(UserActionAvro avro);

    EventSimilarity map(EventSimilarityAvro eventSimilarityAvro);

    RecommendedEventProto map(RecommendedEvent eventRating);
}
