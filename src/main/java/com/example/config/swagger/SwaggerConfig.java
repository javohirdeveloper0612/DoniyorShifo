package com.example.config.swagger;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
//@OpenAPIDefinition(info = @Info(title = "DoniyorShifo.uz APi documentation", version = "1.0", description = ""),servers = {@Server(url = "https://api.doniyor.doniyorshifo.uz/", description = "Default Server URL")})
@OpenAPIDefinition(info = @Info(title = "DoniyorShifo.uz APi documentation", version = "1.0", description = ""))
@ConditionalOnProperty(value = "springfox.documentation.enabled", havingValue = "true", matchIfMissing = true)
@SecurityScheme(name = "Bearer Authentication", type = SecuritySchemeType.HTTP, bearerFormat = "JWT",scheme = "bearer")
public class    SwaggerConfig {

}

