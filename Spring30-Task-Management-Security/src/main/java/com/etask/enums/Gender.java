package com.etask.enums;

public enum Gender {

    MALE("male"), FEMALE("female");

    private final String value;

    private Gender(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
