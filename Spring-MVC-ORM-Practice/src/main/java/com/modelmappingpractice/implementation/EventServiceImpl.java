package com.modelmappingpractice.implementation;

import com.modelmappingpractice.dto.EventDTO;
import com.modelmappingpractice.entity.Event;
import com.modelmappingpractice.mappers.MapperUtil;
import com.modelmappingpractice.repositoty.EventRepository;
import com.modelmappingpractice.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    MapperUtil mapperUtil;

    @Override
    public List<EventDTO> listAllEvent() {

        List<Event>events = eventRepository.findAll();
        return events.stream().map(obj->{return mapperUtil.convert(obj,new EventDTO());}).collect(Collectors.toList());
    }

    @Override
    public void save(EventDTO eventDTO) {
        eventRepository.save(mapperUtil.convert(eventDTO,new Event()));
    }
}
