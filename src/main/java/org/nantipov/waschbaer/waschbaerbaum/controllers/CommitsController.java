package org.nantipov.waschbaer.waschbaerbaum.controllers;

import org.nantipov.waschbaer.waschbaerbaum.domain.ActionCommitDTO;
import org.nantipov.waschbaer.waschbaerbaum.services.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Service
@RestController
@RequestMapping("/api/commits")
public class CommitsController {

    private final ActionService actionService;

    @Autowired
    public CommitsController(ActionService actionService) {
        this.actionService = actionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void commitAction(@RequestBody @Valid ActionCommitDTO actionCommitDTO) {
        actionService.commitAction(actionCommitDTO.getActionId());
    }

}
