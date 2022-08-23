package com.etask.bootstrap;

import com.etask.dto.ProjectDTO;
import com.etask.dto.RoleDTO;
import com.etask.dto.TaskDTO;
import com.etask.dto.UserDTO;
import com.etask.enums.Gender;
import com.etask.enums.Status;
import com.etask.service.ProjectService;
import com.etask.service.RoleService;
import com.etask.service.TaskService;
import com.etask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataGenerator implements CommandLineRunner {

    RoleService roleService;
    UserService userService;
    @Autowired
    ProjectService projectService;
    @Autowired
    TaskService taskService;
    @Autowired
    public DataGenerator(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        RoleDTO adminRole = new RoleDTO(1L, "Admin");
        RoleDTO managerRole = new RoleDTO(2L, "Manager");
        RoleDTO employeeRole = new RoleDTO(3L, "Employee");

       roleService.save(adminRole);
       roleService.save(managerRole);
       roleService.save(employeeRole);

       UserDTO user1 = new UserDTO("John", "Adam","john.adam@mailinator.com",
               "abc", true, "2012220544", managerRole, Gender.MALE);
        UserDTO user2 = new UserDTO("Alica", "Adam","Alica.adam@mailinator.com",
                "abc", true, "2012320544", managerRole, Gender.FEMALE);
        UserDTO user3 = new UserDTO("Mike", "Tyson","mike.tyson@mailinator.com",
                "abc", true, "2012229544", adminRole, Gender.MALE);
        UserDTO user4 = new UserDTO("Sofia", "Adam","sofia.adam@mailinator.com",
                "abc", true, "2012720544", employeeRole, Gender.MALE);
        UserDTO user5 = new UserDTO("John", "Terry","john.Terry@mailinator.com",
                "abc", true, "2012220594", employeeRole, Gender.MALE);
        UserDTO user6 = new UserDTO("John", "Smith","john.smith@mailinator.com",
                "abc", true, "2013320544", managerRole, Gender.MALE);
        UserDTO user7 = new UserDTO("Alica", "Marconi","Alica.marconi@mailinator.com",
                "abc", true, "2012320544", managerRole, Gender.FEMALE);
        UserDTO user8 = new UserDTO("Mike", "Json","mike.json@mailinator.com",
                "abc", true, "2012229544", adminRole, Gender.MALE);
        UserDTO user9 = new UserDTO("Sofia", "Harvey","sofia.harvey@mailinator.com",
                "abc", true, "2012720544", employeeRole, Gender.MALE);
        UserDTO user10 = new UserDTO("Franco", "Terry","franco.Terry@mailinator.com",
                "abc", true, "2012220594", employeeRole, Gender.MALE);

        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        userService.save(user4);
        userService.save(user5);
        userService.save(user6);
        userService.save(user7);
        userService.save(user8);
        userService.save(user9);
        userService.save(user10);

        ProjectDTO project1 = new ProjectDTO("Spring MVC", "PR001", user1, LocalDate.now(), LocalDate.now().plusDays(25 ), "Creating Controllers", Status.OPEN);
        ProjectDTO project2 = new ProjectDTO("Spring ORM", "PR002", user2, LocalDate.now(), LocalDate.now().plusDays(26 ), "Creating DB", Status.IN_PROGRESS);
        ProjectDTO project3 = new ProjectDTO("Spring API", "PR003", user3, LocalDate.now(), LocalDate.now().plusDays(33 ), "Creating API", Status.OPEN);

        projectService.save(project1);
        projectService.save(project2);
        projectService.save(project3);

        TaskDTO task1 = new TaskDTO( project1, user8,"Controller", "Request Mapping", Status.IN_PROGRESS, LocalDate.now().minusDays(4));
        TaskDTO task2 = new TaskDTO(project3, user3,"Mapping", "Many-to-Many", Status.IN_PROGRESS, LocalDate.now().minusDays(9));
        TaskDTO task3 = new TaskDTO( project3, user5,"Configuration", "Database Connection", Status.COMPLETE, LocalDate.now().minusDays(20));
        TaskDTO task4 = new TaskDTO(project2, user7,"DI", "Autowired", Status.UAT_TEST, LocalDate.now().minusDays(6));

        taskService.save(task1);
        taskService.save(task2);
        taskService.save(task3);
        taskService.save(task4);


    }
}
