package ru.practicum.ewm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.practicum.ewm.model.Event;
import ru.practicum.ewm.dto.RequestCountDto;
import ru.practicum.ewm.model.Request;
import ru.practicum.ewm.model.User;

import java.util.List;
import java.util.stream.Stream;

public interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> findAllByEvent(Event event);

    List<Request> findAllByUser(User user);

    @Query("SELECT new ru.practicum.ewm.dto.RequestCountDto(r.event.id, count(r.id)) " +
            "FROM Request r WHERE r.event IN :events " +
            "AND r.status = ru.practicum.ewm.model.RequestStatus.CONFIRMED " +
            "GROUP BY r.event.id")
    Stream<RequestCountDto> findConfirmedRequest(@Param("events") List<Event> events);
}
