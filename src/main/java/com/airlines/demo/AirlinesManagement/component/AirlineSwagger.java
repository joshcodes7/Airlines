package com.airlines.demo.AirlinesManagement.component;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AirlineSwagger {

    @Bean
    public OpenAPI airlineopenapi(){
        return new OpenAPI()
                .info(new Info()
                        .title("Airlines Swagger")
                        .description("Airlines Documentation information related to flight.")
                        .version("0.1"));
    }
}
