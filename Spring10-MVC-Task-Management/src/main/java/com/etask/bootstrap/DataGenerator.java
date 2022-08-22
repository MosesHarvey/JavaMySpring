package com.etask.bootstrap;

import com.etask.dto.ProjectDTO;
import com.etask.dto.RoleDTO;
import com.etask.dto.UserDTO;
import com.etask.enums.Gender;
import com.etask.enums.Status;
import com.etask.implementation.RoleServiceImpl;
import com.etask.service.ProjectService;
import com.etask.service.RoleService;
import com.etask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataGenerator implements CommandLineRunner {

    RoleService roleService;
    UserService userService;
    @Autowired
    ProjectService projectService;

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

        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        userService.save(user4);
        userService.save(user5);


        ProjectDTO project1 = new ProjectDTO("Spring MVC", "PR001", user1, LocalDate.now(), LocalDate.now().plusDays(25 ), "Creating Controllers", Status.OPEN);
        ProjectDTO project2 = new ProjectDTO("Spring ORM", "PR002", user2, LocalDate.now(), LocalDate.now().plusDays(26 ), "Creating DB", Status.IN_PROGRESS);
        ProjectDTO project3 = new ProjectDTO("Spring API", "PR003", user3, LocalDate.now(), LocalDate.now().plusDays(33 ), "Creating API", Status.OPEN);

        projectService.save(project1);
        projectService.save(project2);
        projectService.save(project3);
    }
}
