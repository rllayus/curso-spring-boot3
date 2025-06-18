package com.upb.modulo_01.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Profile;

@OpenAPIDefinition(
        info = @Info(
                title = "APIs del módulo",
                version = "v1",
                description = "Esta aplicación  de prubea",
                contact = @Contact(
                        name = "Ricardo Laredo",
                        email = "rllayus@gmail.com"
                )
        ),
        servers = {
                @Server(
                        url = "http://localhost:8082",
                        description = " Servidor de desarrollo"
                ), @Server(
                url = "http://localhost:8081",
                description = " Servidor de desarrollo"
        )
        }
)
@SecurityScheme(
        name = "bearerToken",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER,
        scheme = "bearer",
        bearerFormat = "jwt"
)
@Profile({"dev", "local", "swagger"})
public class OpenApiConfig {
}
