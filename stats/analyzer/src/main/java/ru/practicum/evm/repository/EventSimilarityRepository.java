package ru.practicum.evm.repository;

import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.practicum.evm.dto.EventSimilarityCoef;
import ru.practicum.evm.dto.RecommendedEvent;
import ru.practicum.evm.model.EventSimilarity;
import ru.practicum.evm.model.SimilarityId;

import java.util.stream.Stream;

public interface EventSimilarityRepository extends JpaRepository<EventSimilarity, SimilarityId> {

    Stream<EventSimilarity> findDistinctByEventAOrEventB(long eventA, long eventB);


    @Query("SELECT new ru.practicum.evm.dto.RecommendedEvent(" +
                "case when s.eventA IN :eventIds then s.eventB else s.eventA end, s.score" +
            ")" +
            "FROM EventSimilarity s " +
            "WHERE s.eventA IN :eventIds OR s.eventB IN :eventIds " +
            "ORDER BY s.score DESC")
    Stream<RecommendedEvent> findSimilarEvent(@Param("eventIds") Iterable<Long> eventId, Limit limit);

    @Query("SELECT new ru.practicum.evm.dto.EventSimilarityCoef(" +
            "case when s.eventA IN :similarEventIds then s.eventA else s.eventB end, " +
            "case when s.eventB IN :similarEventIds then s.eventB else s.eventA end," +
            "s.score) " +
            "FROM EventSimilarity s " +
            "WHERE (s.eventA IN :similarEventIds AND s.eventB IN :eventIds) " +
            "OR (s.eventB IN :similarEventIds AND s.eventA IN :eventIds)")
    Stream<EventSimilarityCoef> findSimilarity(@Param("similarEventIds") Iterable<Long> similarEventIds,
                                               @Param("eventIds") Iterable<Long> eventWithInteractionIds);


}
