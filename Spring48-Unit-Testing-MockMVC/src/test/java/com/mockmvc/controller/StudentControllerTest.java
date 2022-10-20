package com.mockmvc.controller;

import com.mockmvc.entity.Student;
import com.mockmvc.service.StudentService;
;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    StudentService studentService;


    @Test
    void getStudent_Service() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/student/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{ \"id\": null,  \"firstName\": \"Mike\",  \"lastName\": \"Smith\", \"age\": 23}"))
                .andReturn();
    }

    @Test
    void jsonAssert() throws JSONException {
        String actual = "{ \"id\": null,  \"firstName\": \"Mike\",  \"lastName\": \"Smith\", \"age\": 23}";
        String expected =  "{ \"id\": null,  \"firstName\": \"Mike\",  \"lastName\": \"Smith\", \"age\": 23}";

        JSONAssert.assertEquals(expected,actual, false);
    }

    @Test
    void jsonAssertWithoutEscapeCharacter() throws JSONException {
        String actual = "{ id: null,  firstName: Mike,  lastName: Smith, age: 23}";
        String expected =  "{ id:null,firstName:Mike,lastName:Smith,age:23}";

        JSONAssert.assertEquals(expected,actual, false);
    }

    //abstract data layer

    @Test
    void getStudents() throws Exception {
        when(studentService.getStudent_Data()).thenReturn(Arrays.asList(
                new Student("Jennifer", "Topaz", 23),
                new Student("John", "Adam", 26)
                ));

        mockMvc.perform(MockMvcRequestBuilders.get("/student/all").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{ \"id\": null,  \"firstName\": \"Jennifer\",  \"lastName\": \"Topaz\", \"age\": 23}, { \"id\": null,  \"firstName\": \"John\",  \"lastName\": \"Adam\", \"age\": 26}]"))
                .andReturn();

    }
}