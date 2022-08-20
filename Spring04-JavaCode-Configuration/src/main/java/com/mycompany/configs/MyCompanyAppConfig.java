package com.mycompany.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

// Config file replaces the config.xml file using only 3 lines code

@Configuration
@ComponentScan("com.mycompany")
public class MyCompanyAppConfig {
}
