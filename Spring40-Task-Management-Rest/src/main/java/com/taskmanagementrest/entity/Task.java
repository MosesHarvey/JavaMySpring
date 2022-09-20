package com.taskmanagementrest.entity;


import com.taskmanagementrest.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="tasks")
@Where(clause="is_deleted=false")
public class Task extends BaseEntity {



    private String taskSubject;
    private String taskDetail;

    @Enumerated(EnumType.STRING)
    private Status taskStatus;

    private LocalDate assignedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User assignedEmployee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="project_id")
    private Project project;
}
