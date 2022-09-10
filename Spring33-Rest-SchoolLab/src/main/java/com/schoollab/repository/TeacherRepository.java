package com.schoollab.repository;

import com.schoollab.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
