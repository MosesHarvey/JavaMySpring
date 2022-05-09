package services;

import interfaces.Mentor;

public class FulTimeMentor implements Mentor {

    @Override
    public void createAccount(){
        System.out.println("Full Time mentor is created");
    }
}
