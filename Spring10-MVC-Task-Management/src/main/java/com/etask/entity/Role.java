package com.etask.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Role {

    private Long id;
    private String description;

    public Role(Long id, String description) {
        this.id = id;
        this.description = description;
    }
}