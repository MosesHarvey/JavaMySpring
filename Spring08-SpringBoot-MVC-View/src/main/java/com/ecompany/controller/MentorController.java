package com.ecompany.controller;

import com.ecompany.enums.Gender;
import com.ecompany.model.Mentor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/mentor")
public class MentorController {

    @GetMapping("/list")
    public String showTable(Model model){
        List<Mentor> mentorList = new ArrayList<>();
        mentorList.add(new Mentor("Mike", "Smith", 45, Gender.MALE));
        mentorList.add(new Mentor("Mike", "Tyson", 60, Gender.MALE));
        mentorList.add(new Mentor("Ariana", "Grande", 23, Gender.FEMALE));

        model.addAttribute("mentors", mentorList);
        return "mentor/mentor-list";

    }
}
