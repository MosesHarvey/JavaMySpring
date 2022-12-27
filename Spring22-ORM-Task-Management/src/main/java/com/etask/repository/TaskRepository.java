package com.etask.repository;

import com.etask.entity.Project;
import com.etask.entity.Task;
import com.etask.entity.User;
import com.etask.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT COUNT(t) FROM Task t WHERE t.project.projectCode = ?1 AND t.taskStatus <> 'COMPLETED'")
    int totalNonCompletedTasks(String projectCode);

    @Query(value = "SELECT COUNT(*) FROM tasks t JOIN projects p on t.project_id = p.id WHERE p.project_code = ?1 AND" +
            " t.task_status = 'COMPLETED'", nativeQuery = true)
    int totalCompletedTasks(String projectCode);

    List<Task> findAllByProject(Project project);
    List<Task>findAllByTaskStatusIsNotAndAssignedEmployee(Status status, User user);

    List<Task> findAllByProjectAssignedManager(User user);
    List<Task>findAllByAssignedEmployee(User user);


    @Modifying
    @Transactional
    @Query(value="update tasks set is_deleted = true where id = ?1", nativeQuery = true)
    void changeIsDeletedById(Long id);


    @Modifying
    @Transactional
    @Query(value = "update tasks set task_status='COMPLETE' where id=?1", nativeQuery = true)
    void completeTaskById(Long id);

    List<Task> findAllByTaskStatusAndAssignedEmployee(Status status, User user);
}
