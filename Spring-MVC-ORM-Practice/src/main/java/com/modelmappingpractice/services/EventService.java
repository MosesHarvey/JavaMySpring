package com.modelmappingpractice.services;

import com.modelmappingpractice.dto.EventDTO;

import java.util.List;

public interface EventService {
    List<EventDTO> listAllEvent();

    void save(EventDTO eventDTO);
}
