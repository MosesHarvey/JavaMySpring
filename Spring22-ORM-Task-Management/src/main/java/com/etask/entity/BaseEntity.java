package com.etask.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime insertDateTime;
    private Long insertUserId;
    private LocalDateTime lastUpdatedDateTime;
    private Long lastUpdatedUserId;

    private boolean isDeleted=false;

    @PrePersist                            // before save
    private void onPrePersist(){
        this.insertDateTime = LocalDateTime.now();
        this.lastUpdatedDateTime=LocalDateTime.now();
        this.insertUserId=1L;
        this.lastUpdatedUserId=1L;
    }

    @PreUpdate
    private void onPreUpdate(){
        this.lastUpdatedDateTime=LocalDateTime.now();
        this.lastUpdatedUserId=1L;
    }


}
