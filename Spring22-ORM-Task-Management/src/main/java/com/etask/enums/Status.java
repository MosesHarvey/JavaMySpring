package com.etask.enums;

public enum Status {
    OPEN("Open"), IN_PROGRESS("In Progress"), UAT_TEST("UAT Testing"), COMPLETE("Completed; ");

    private final String value;

    private Status(String value) {
       this.value = value;
    }
}

