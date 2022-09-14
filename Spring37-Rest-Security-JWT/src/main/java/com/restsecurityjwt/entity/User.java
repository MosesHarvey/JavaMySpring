package com.restsecurityjwt.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.restsecurityjwt.enums.UserRole;
import com.restsecurityjwt.enums.UserState;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="users")
@Where(clause = "is_deleted=false")
public class User extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    @Enumerated(EnumType.STRING)
    private UserState userState;

    @Column(name="is_verified")
    private Boolean isVerified;
}
