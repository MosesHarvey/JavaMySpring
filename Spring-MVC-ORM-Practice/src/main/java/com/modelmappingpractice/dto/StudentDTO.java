package com.modelmappingpractice.dto;

import com.modelmappingpractice.entity.Passport;
import com.modelmappingpractice.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentDTO {

    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Status status;
    private String PassportNo;
    private String issuedGovernment;
    private String issuedDate;
    private String expiredDate;

}
