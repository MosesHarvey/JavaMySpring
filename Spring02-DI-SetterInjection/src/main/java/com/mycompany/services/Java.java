package com.mycompany.services;


import com.mycompany.interfaces.Course;

public class Java implements Course {

    OfficeHours officeHours;

    public OfficeHours getOfficeHours() {
        return officeHours;
    }

    public void setOfficeHours(OfficeHours officeHours) {
        this.officeHours = officeHours;
    }


    public void getTeachingHours() {
        System.out.println("Weekly teaching hours: "+(20+ officeHours.getHours()));
    }
}
