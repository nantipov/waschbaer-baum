package org.nantipov.waschbaer.waschbaerbaum.domain;

import lombok.Data;

import java.util.UUID;
import javax.validation.constraints.NotNull;

@Data
public class ActionDTO {
    private UUID id;

    @NotNull
    private ActionType type;

    private String argumentsData;
}
