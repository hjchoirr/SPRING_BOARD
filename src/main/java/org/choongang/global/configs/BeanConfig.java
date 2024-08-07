package org.choongang.global.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfig {

    public ObjectMapper om = new ObjectMapper();


    @Bean
    public RestTemplate restTemplate() {
        om.registerModule(new JavaTimeModule());
        return new RestTemplate();
    }
}
