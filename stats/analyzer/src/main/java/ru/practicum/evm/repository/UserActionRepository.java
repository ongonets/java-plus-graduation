package ru.practicum.evm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.practicum.evm.dto.RecommendedEvent;
import ru.practicum.evm.model.UserAction;

import java.util.List;
import java.util.stream.Stream;

public interface UserActionRepository extends JpaRepository<UserAction, Long> {

    Stream<UserAction> findByUserId(Long userId);

    @Query("SELECT new ru.practicum.evm.dto.RecommendedEvent(w.event, SUM (w.maxWeight)) " +
            "FROM ( " +
            "SELECT u.eventId AS event, u.userId AS user_id, MAX (u.weight) AS maxWeight " +
            "FROM UserAction AS u " +
            "WHERE u.eventId IN :ids " +
            "GROUP BY u.eventId, u.userId) AS w " +
            "GROUP BY w.event " +
            "ORDER BY SUM (w.maxWeight)")
    Stream<RecommendedEvent> getRating(@Param("ids") List<Long> ids);

    @Query("SELECT new ru.practicum.evm.dto.RecommendedEvent(w.event, SUM (w.maxWeight)) " +
            "FROM ( " +
            "SELECT u.eventId AS event, u.userId AS user_id, MAX (u.weight) AS maxWeight " +
            "FROM UserAction AS u " +
            "WHERE u.userId = :userId " +
            "GROUP BY u.eventId, u.userId) AS w " +
            "GROUP BY w.event " +
            "ORDER BY SUM (w.maxWeight)")
    Stream<RecommendedEvent> getUserRating(@Param("userId") Long userId);
}
