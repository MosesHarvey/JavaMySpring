package com.modelmappingpractice.dto;


import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PassportDTO {

    private String PassportNo;
    private String issuedGovernment;
    private String issuedDate;
    private String expiredDate;


}
