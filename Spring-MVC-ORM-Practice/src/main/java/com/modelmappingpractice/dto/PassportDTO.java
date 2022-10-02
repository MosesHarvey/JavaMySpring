package com.modelmappingpractice.dto;


import lombok.ToString;

import java.time.LocalDate;

@ToString
public class PassportDTO {

    private String PassportNo;
    private String issuedGovernment;
    private String issuedDate;
    private String expiredDate;


}
