package mycompany.services;

import mycompany.interfaces.Course;
import mycompany.interfaces.ExtraSessions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class Java implements Course {

    @Autowired
    @Qualifier
    private ExtraSessions extraSessions;
//    @Autowired
//    public Java(ExtraSessions extraSessions) {
//        this.extraSessions = extraSessions;
//    }

    @Override
    public void getTeachingHours() {
        System.out.println("Teaching Hours: " + extraSessions.getHours());

    }
}
