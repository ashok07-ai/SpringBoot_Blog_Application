package com.blog_application.app.BlogApplication.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI swaggerCustomConfiguration(){
        return new OpenAPI().info(
                new Info().title("Blog Application")
                        .description("The application used to manage blogs")
        ).servers(
                List.of(
                        new Server().url("http://localhost:8080").description("development"),
                        new Server().url("http://blogapplication.com").description("production")
                )
        ).tags(
                List.of(
                        new Tag().name("User Controller"),
                        new Tag().name("Category Controller"),
                        new Tag().name("Post Controller")
                )
        ).addSecurityItem(
                new SecurityRequirement().addList("bearerAuth"))
                .components(
                        new Components().addSecuritySchemes(
                                "bearerAuth", new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("Bearer")
                                        .bearerFormat("JWT")
                                        .in(SecurityScheme.In.HEADER)
                                        .name("Authorization")
                                )

                );
    }
}


