package org.nantipov.waschbaer.waschbaerbaum.repository;

import org.nantipov.waschbaer.waschbaerbaum.domain.EventEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.ZonedDateTime;
import java.util.List;

public interface EventRepository extends CrudRepository<EventEntity, Long> {

    List<EventEntity> findByCreatedAtBetween(ZonedDateTime startTime, ZonedDateTime endTime);

}
