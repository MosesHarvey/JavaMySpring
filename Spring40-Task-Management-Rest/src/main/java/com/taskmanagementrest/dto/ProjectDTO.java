package com.taskmanagementrest.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.taskmanagementrest.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(value = {"hibernateInitializer"}, ignoreUnknown = true)
public class ProjectDTO {
    private Long id;
    private String projectName;
    private String projectCode;
    private UserDTO assignedManager;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private String projectDetail;
    private Status projectStatus;
    private int completeTaskCounts;
    private int inCompleteTaskCounts;


}
