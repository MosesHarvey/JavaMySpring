package mycompany.services;

import mycompany.interfaces.Course;

public class API implements Course {
    @Override
    public void getTeachingHours() {
        System.out.println("Teaching Hours 5");
    }
}
