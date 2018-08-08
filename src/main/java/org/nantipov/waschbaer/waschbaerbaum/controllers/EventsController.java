package org.nantipov.waschbaer.waschbaerbaum.controllers;

import org.nantipov.waschbaer.waschbaerbaum.domain.ContainerDTO;
import org.nantipov.waschbaer.waschbaerbaum.domain.EventDTO;
import org.nantipov.waschbaer.waschbaerbaum.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import javax.validation.Valid;

@Service
@RestController
@RequestMapping("/api/events")
public class EventsController {

    private final EventService eventService;

    @Value("${waschbaer.default-events-time-window}")
    private int defaultTimePeriod;

    @Autowired
    public EventsController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postEvent(@RequestBody @Valid EventDTO eventDTO) {
        eventService.postEvent(eventDTO);
    }

    @GetMapping
    public ContainerDTO<EventDTO> getEvents(
            @RequestParam(name = "start_time", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                    ZonedDateTime startTime,
            @RequestParam(name = "end_time", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                    ZonedDateTime endTime) {
        ZonedDateTime startTimeToProcess =
                startTime != null ? startTime : ZonedDateTime.now().minusMinutes(defaultTimePeriod);

        ZonedDateTime endTimeToProcess = endTime != null ? endTime : ZonedDateTime.now();

        return ContainerDTO.of(eventService.getEvents(startTimeToProcess, endTimeToProcess));
    }

}
