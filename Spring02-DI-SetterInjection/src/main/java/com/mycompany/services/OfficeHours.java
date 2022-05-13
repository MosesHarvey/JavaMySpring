package com.mycompany.services;

import com.mycompany.interfaces.ExtraSessions;

public class OfficeHours implements ExtraSessions {
    @Override
    public int getHours(){
      return 10;
    }
}
