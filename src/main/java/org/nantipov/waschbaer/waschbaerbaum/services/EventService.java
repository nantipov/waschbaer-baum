package org.nantipov.waschbaer.waschbaerbaum.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.nantipov.waschbaer.waschbaerbaum.domain.EventDTO;
import org.nantipov.waschbaer.waschbaerbaum.domain.EventEntity;
import org.nantipov.waschbaer.waschbaerbaum.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EventService {

    private static final TypeReference<Map<String, Object>> MAP_TYPE = new MapTypeReference();

    private final EventRepository eventRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public EventService(EventRepository eventRepository, ObjectMapper objectMapper) {
        this.eventRepository = eventRepository;
        this.objectMapper = objectMapper;
    }

    public void postEvent(EventDTO eventDTO) {
        EventEntity eventEntity = new EventEntity();
        eventEntity.setEventId(UUID.randomUUID());
        eventEntity.setEventType(eventDTO.getType());
        eventEntity.setCreatedAt(ZonedDateTime.now());
        eventEntity.setDataValue(buildJson(eventDTO.getData()));
        eventRepository.save(eventEntity);
    }

    public List<EventDTO> getEvents(ZonedDateTime startTime, ZonedDateTime endTime) {
        return eventRepository.findByCreatedAtBetween(startTime, endTime).stream()
                              .map(entity -> {
                                  EventDTO eventDTO = new EventDTO();
                                  eventDTO.setId(entity.getEventId());
                                  eventDTO.setType(entity.getEventType());
                                  eventDTO.setOccuredAt(entity.getCreatedAt());
                                  eventDTO.setData(parseJson(entity.getDataValue()));
                                  return eventDTO;
                              })
                              .collect(Collectors.toList());
    }

    private String buildJson(Map<String, Object> data) {
        try {
            return objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Could not write json", e);
        }
    }

    private Map<String, Object> parseJson(String jsonData) {
        try {
            return objectMapper.readValue(jsonData, MAP_TYPE);
        } catch (IOException e) {
            throw new IllegalArgumentException("Could not parse json", e);
        }
    }

    private static class MapTypeReference extends TypeReference<Map<String, Object>> {
        private MapTypeReference() {

        }
    }

}
