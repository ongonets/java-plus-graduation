package ru.practicum.evm.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Entity
@Table(name = "weights")
@IdClass(SimilarityId.class)
@Getter
@Setter
@ToString
public class EventSimilarity {

    @Id
    @Column(name = "id_event_a")
    private long eventA;

    @Id
    @Column(name = "id_event_b")
    private long eventB;

    private double score;

    @Column(name = "created")
    private Instant timestamp;
}
