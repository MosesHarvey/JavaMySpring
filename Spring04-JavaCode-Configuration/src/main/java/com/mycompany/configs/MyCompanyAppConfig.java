package com.mycompany.configs;


import com.mycompany.services.Java;
import com.mycompany.services.OfficeHours;
import com.mycompany.services.Selenium;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// Config file replaces the config.xml file using only 3 lines code

@Configuration
@ComponentScan("com.mycompany")
public class MyCompanyAppConfig {



    // in order to remove @component annotation, below code is an example:
    @Bean
    public Java java(){
        return new Java(officeHours());
    }

    @Bean
    public Selenium selenium(){
        return new Selenium();
    }

    @Bean
    public OfficeHours officeHours(){
        return new OfficeHours();
    }

}
