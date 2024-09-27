package com.pethost.pethost.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
   @Bean
    public OpenAPI customOpenAPI() {
       return new OpenAPI()
               .info(new Info()
                       .title("PetHost API")
                       .description("descrição da api")
                       .contact(new Contact().name("PETHOST")
                       ).version("1.0.0"));
   }
}
