package org.nantipov.waschbaer.waschbaerbaum.domain;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.Map;
import java.util.UUID;
import javax.validation.constraints.NotNull;

@Data
public class EventDTO {
    private UUID id;

    @NotNull
    private EventType type;

    private Map<String, Object> data;

    @NotNull
    private ZonedDateTime occurredAt;
}
