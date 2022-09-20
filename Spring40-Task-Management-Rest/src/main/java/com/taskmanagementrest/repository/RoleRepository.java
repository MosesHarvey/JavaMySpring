package com.taskmanagementrest.repository;


import com.taskmanagementrest.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByDescription(String description);

}
