package com.mycompany.services;

import com.mycompany.interfaces.ExtraSessions;

public class OfficeHours implements ExtraSessions {
    @Override
    public void getHours(){
        System.out.println("weekly extra hours : 10");
    }
}
