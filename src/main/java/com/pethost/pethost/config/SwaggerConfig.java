package com.pethost.pethost.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
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
                       ).version("1.0.0"))
               .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
               .components(new Components().addSecuritySchemes("bearerAuth",
                       new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
   }
}
