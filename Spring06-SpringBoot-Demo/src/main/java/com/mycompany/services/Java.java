package com.mycompany.services;

import com.mycompany.interfaces.Course;
import com.mycompany.interfaces.ExtraSessions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class Java implements Course {

//    @Autowired
//    @Qualifier("officeHours")
    private ExtraSessions extraSessions;

//    @Autowired

    public Java( @Qualifier("officeHours") ExtraSessions extraSessions) {
        this.extraSessions = extraSessions;
    }

    @Override
    public void getTeachingHours() {
        System.out.println("Weekly Teaching Hours: " + (20+ extraSessions.getHours()));


    }
}