package org.nantipov.waschbaer.waschbaerbaum.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.ZonedDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "action")
public class ActionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private UUID actionId;

    @Column(nullable = false)
    private ActionType actionType;

    @Column
    private String argumentsData;

    @CreationTimestamp
    private ZonedDateTime createdAt;

    @Column
    private ZonedDateTime committedAt;
}
