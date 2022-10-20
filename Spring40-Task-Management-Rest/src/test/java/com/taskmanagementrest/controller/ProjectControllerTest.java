package com.taskmanagementrest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.taskmanagementrest.dto.ProjectDTO;
import com.taskmanagementrest.dto.RoleDTO;
import com.taskmanagementrest.dto.UserDTO;
import com.taskmanagementrest.enums.Gender;
import com.taskmanagementrest.enums.Status;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @Test
    public void givenNoToken_whenGetSecureRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/project/TC002"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void givenToken_getAllProjects() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/project")
                .header("Authorization", token)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].projectCode").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].assignedManager.userName").isNotEmpty());

    }

    // test post
    @Test void givenToken_createProject() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/project/create")
                .header("Authorization", token)
                .content(toJsonString(projectDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("projectCode").isNotEmpty());
    }


    @Test
    public void givenToken_updateProject() throws Exception {
    projectDTO.setId(2L);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/v1/project/update")
                        .header("Authorization", token)
                        .content(toJsonString(projectDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("message").value("Project has been updated successfully"));

    }

    protected String toJsonString(final Object obj){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS,false);
        objectMapper.registerModule(new JavaTimeModule());
        try{
            return objectMapper.writeValueAsString(obj);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }





}