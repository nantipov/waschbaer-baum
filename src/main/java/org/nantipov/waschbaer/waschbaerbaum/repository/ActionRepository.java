package org.nantipov.waschbaer.waschbaerbaum.repository;

import org.nantipov.waschbaer.waschbaerbaum.domain.ActionEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface ActionRepository extends CrudRepository<ActionEntity, Long> {

    List<ActionEntity> findByCommittedAtIsNull();

    ActionEntity findByActionId(UUID actionId);

}
