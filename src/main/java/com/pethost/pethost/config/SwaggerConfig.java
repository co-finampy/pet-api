package com.pethost.pethost.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@com.pethost.pethost.config.EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket petsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("PetsController")) // Altere para o pacote onde suas APIs estão
                .paths(regex("/v1"))
                .build()
                .apiInfo(apiInfo()); // Adicione informações adicionais, se necessário
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Pet Host API", // Título da API
                "API para gerenciar pets", // Descrição
                "1.0", // Versão
                "Terms of service URL", // URL dos termos de serviço
                new Contact("Seu Nome", "www.seusite.com", "seuemail@dominio.com"), // Informações de contato
                "License", // Licença
                "License URL", // URL da licença
                Collections.emptyList() // Extensões
        );
    }
}
