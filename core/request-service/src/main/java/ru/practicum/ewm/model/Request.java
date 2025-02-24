package ru.practicum.ewm.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "requests")
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Request {

    public Request() {
    }

    public Request(Event event, User user) {
        this.event = event;
        this.user = user;
        status = RequestStatus.PENDING;
        created = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    Event event;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @Enumerated(value = EnumType.ORDINAL)
    RequestStatus status;

    LocalDateTime created;
}
