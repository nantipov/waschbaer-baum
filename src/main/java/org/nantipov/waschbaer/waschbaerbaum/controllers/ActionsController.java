package org.nantipov.waschbaer.waschbaerbaum.controllers;

import org.nantipov.waschbaer.waschbaerbaum.domain.ActionDTO;
import org.nantipov.waschbaer.waschbaerbaum.domain.ContainerDTO;
import org.nantipov.waschbaer.waschbaerbaum.services.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Service
@RestController
@RequestMapping("/api/actions")
public class ActionsController {

    private final ActionService actionService;

    @Autowired
    public ActionsController(ActionService actionService) {
        this.actionService = actionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void placeAction(@RequestBody @Valid ActionDTO actionRequest) {
        actionService.placeAction(actionRequest);
    }

    @GetMapping
    public ContainerDTO<ActionDTO> getUncommittedActions() {
        return ContainerDTO.of(actionService.getUncommittedActions());
    }

}
