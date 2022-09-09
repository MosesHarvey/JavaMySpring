package com.etask.repository;

import com.etask.entity.Project;
import com.etask.entity.User;
import com.etask.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {

    Project findByProjectCode(String code);
    List<Project> findAllByAssignedManager(User manager);

    List<Project>findAllByProjectStatusIsNot(Status status);
}
