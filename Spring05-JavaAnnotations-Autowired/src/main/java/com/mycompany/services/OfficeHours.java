package com.mycompany.services;

import com.mycompany.interfaces.Course;
import com.mycompany.interfaces.ExtraSessions;
import org.springframework.stereotype.Component;

@Component
public class OfficeHours implements ExtraSessions {


    @Override
    public int getHours() {
        return 5;
    }
}
