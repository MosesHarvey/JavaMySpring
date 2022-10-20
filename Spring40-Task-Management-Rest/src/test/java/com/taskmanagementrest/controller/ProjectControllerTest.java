package com.taskmanagementrest.controller;

import com.taskmanagementrest.dto.ProjectDTO;
import com.taskmanagementrest.dto.RoleDTO;
import com.taskmanagementrest.dto.UserDTO;
import com.taskmanagementrest.enums.Gender;
import com.taskmanagementrest.enums.Status;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

//integration testing

@SpringBootTest
@AutoConfigureMockMvc
class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final String token = "";

    static UserDTO userDTO;
    static ProjectDTO projectDTO;

    @BeforeAll
    static void setUp(){

        userDTO = UserDTO.builder()
                .id(10L)
                .firstName("Mike")
                .lastName("Apache")
                .userName("mike.apache@abc.com")
                .password("abc123")
                .confirmPassword("abc123")
                .role(new RoleDTO(2L, "Manager"))
                .gender(Gender.MALE)
                .build();

        projectDTO = ProjectDTO.builder()
                .projectCode("TC002")
                .projectName("Socio-phobia")
                .assignedManager(userDTO)
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(5))
                .projectDetail("Discussion of socio-phobia among youth")
                .projectStatus(Status.OPEN)
                .completeTaskCounts(0)
                .inCompleteTaskCounts(0)
                .build();

    }


}