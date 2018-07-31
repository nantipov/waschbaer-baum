package org.nantipov.waschbaer.waschbaerbaum.domain;

import lombok.Data;

import java.util.UUID;
import javax.validation.constraints.NotNull;

@Data
public class ActionCommitDTO {
    @NotNull
    private UUID actionId;
}
