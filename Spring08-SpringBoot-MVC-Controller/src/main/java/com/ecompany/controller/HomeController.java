package com.ecompany.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String getRequestMapping(){
        return "welcome";
    }



    @RequestMapping(method = RequestMethod.GET, value = "/ecompany")
    public String getRequesMapping2(){
        return "home/home";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/moses")
    public String getRequesMapping3(){
        return "welcome";
    }

//    @GetMapping("/ecompany")
//    public String getMappingEx(){
//        return "home/home";
//    }
//
//
//    @PostMapping("/ecompany")
//    public String getMappingEx2(){
//        return "home/home";
//    }


    @GetMapping("/ecompany/{name}")
    public String pathVariableEx(@PathVariable("name") String name){
        System.out.println("name is "+name);
        return "welcome";
    }

    @GetMapping("/ecompany/{name}/{email}")
    public String pathVariableEx2(@PathVariable("name") String name,@PathVariable("email") String email){
        System.out.println("name is "+name);
        System.out.println("email is "+email);
        return "welcome";
    }

    @GetMapping("/ecompany/course")
    public String requestParamEx(@RequestParam("course") String course){
        System.out.println("course name is "+course);
        return "welcome";
    }

    @GetMapping(value = "/course")
    public String requestParamEx2(@RequestParam(value = "course", required = false, defaultValue = "Literature") String course){
        System.out.println("course name is "+course);
        return "/home/home";
    }





}
