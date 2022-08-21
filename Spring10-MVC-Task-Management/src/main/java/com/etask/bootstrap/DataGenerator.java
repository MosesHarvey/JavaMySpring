package com.etask.bootstrap;

import com.etask.dto.RoleDTO;
import com.etask.dto.UserDTO;
import com.etask.enums.Gender;
import com.etask.implementation.RoleServiceImpl;
import com.etask.service.RoleService;
import com.etask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class DataGenerator implements CommandLineRunner {

    RoleService roleService;
    UserService userService;

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

    }
}
