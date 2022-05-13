package com.mycompany.services;

import com.mycompany.interfaces.Course;

public class Java implements Course {

    OfficeHours officeHours;

   public Java(OfficeHours officeHours){
       this.officeHours =officeHours;
   }

    @Override
    public void getTeachingHours() {
        System.out.println("Weekly teaching hours: "+(20+ officeHours.getHours()));
    }
}
