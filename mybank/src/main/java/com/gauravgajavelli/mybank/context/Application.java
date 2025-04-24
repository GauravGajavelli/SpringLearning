package com.gauravgajavelli.mybank.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.gauravgajavelli.mybank.Main;
import com.gauravgajavelli.mybank.service.TransactionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackageClasses = Main.class)
//@PropertySource("classpath:/application.properties")
//@PropertySource(value = "classpath:/application-${spring.profiles.active}.properties"
public class Application {

    @Bean
    public ObjectMapper objectMapper() {
        return JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
    }
}
