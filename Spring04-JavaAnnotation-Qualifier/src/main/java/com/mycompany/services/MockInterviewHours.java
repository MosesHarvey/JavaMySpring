package com.mycompany.services;

import com.mycompany.interfaces.ExtraSessions;
import org.springframework.stereotype.Component;

@Component
public class MockInterviewHours implements ExtraSessions {
    @Override
    public int getHours() {
     return 3;
    }
}
