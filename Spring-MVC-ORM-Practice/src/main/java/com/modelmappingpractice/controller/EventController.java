package com.modelmappingpractice.controller;

import com.modelmappingpractice.dto.EventDTO;
import com.modelmappingpractice.enums.Status;
import com.modelmappingpractice.mappers.MapperUtil;
import com.modelmappingpractice.services.DepartmentService;
import com.modelmappingpractice.services.PassportService;
import com.modelmappingpractice.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private PassportService passportService;

    @Autowired
    private MapperUtil mapperUtil;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/list")
    public String getEventList(Model model){
        model.addAttribute("events", eventService.listAllEvent());
        return "/administration/event-list";
    }

    @GetMapping("/form")
    public String getEvent(Model model){


        model.addAttribute("event", new EventDTO());
        return "/administration/event-form";
    }

    @PostMapping("/form")
    public String insertEvent(@ModelAttribute EventDTO eventDTO) {


        eventService.save(eventDTO);


        return "redirect:/event/form";
    }
}
