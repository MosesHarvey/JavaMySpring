package com.mycompany.services;

import com.mycompany.interfaces.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Java implements Course {

    private OfficeHours officeHours;

    // constructor injection
   // @Autowired
    //public Java(OfficeHours officeHours) {
//        this.officeHours = officeHours;
//    }


    public void setOfficeHours(OfficeHours officeHours) {
        this.officeHours = officeHours;
    }

    @Override
    public void getTeachingHours() {
        System.out.println("Weekly Teaching Hours: " + (20+ officeHours.getHours()));

    }
}
