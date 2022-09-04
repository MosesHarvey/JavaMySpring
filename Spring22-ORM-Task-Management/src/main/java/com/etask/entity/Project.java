package com.etask.entity;

import com.etask.dto.UserDTO;
import com.etask.enums.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="projects")
@Where(clause="is_deleted=false")
public class Project extends BaseEntity{
    private String projectCode;
    private String projectName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="manager_id")
    private User assignedManager;


    private LocalDate startDate;

    private LocalDate endDate;

    private Status projectStatus;
    private String projectDetail;


}
