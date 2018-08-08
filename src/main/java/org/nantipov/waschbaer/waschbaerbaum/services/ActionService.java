package org.nantipov.waschbaer.waschbaerbaum.services;

import lombok.extern.slf4j.Slf4j;
import org.nantipov.waschbaer.waschbaerbaum.domain.ActionDTO;
import org.nantipov.waschbaer.waschbaerbaum.domain.ActionEntity;
import org.nantipov.waschbaer.waschbaerbaum.repository.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ActionService {

    private final ActionRepository actionRepository;

    @Autowired
    public ActionService(ActionRepository actionRepository) {
        this.actionRepository = actionRepository;
    }

    public void placeAction(ActionDTO actionRequest) {
        log.debug("Posting action {}", actionRequest);
        ActionEntity actionEntity = new ActionEntity();
        actionEntity.setActionId(UUID.randomUUID());
        actionEntity.setActionType(actionRequest.getType());
        actionEntity.setArgumentsData(actionRequest.getArgumentsData());
        actionRepository.save(actionEntity);
    }

    public List<ActionDTO> getUncommittedActions() {
        return actionRepository.findByCommittedAtIsNull()
                               .stream()
                               .map(entity -> {
                                   ActionDTO actionDTO = new ActionDTO();
                                   actionDTO.setId(entity.getActionId());
                                   actionDTO.setType(entity.getActionType());
                                   actionDTO.setArgumentsData(entity.getArgumentsData());
                                   return actionDTO;
                               })
                               .collect(Collectors.toList());
    }

    public void commitAction(UUID actionId) {
        log.debug("Committing action {}", actionId);
        ActionEntity actionEntity = actionRepository.findByActionId(actionId);
        if (actionEntity == null) {
            throw new IllegalStateException("not found"); // Problem 404
        }
        actionEntity.setCommittedAt(ZonedDateTime.now());
        actionRepository.save(actionEntity);
    }

}
