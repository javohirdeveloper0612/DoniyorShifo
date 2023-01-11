package com.example.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Doniyorshifo.uz APi documentation", version = "1.0", description = ""))
@ConditionalOnProperty(value = "springfox.documentation.enabled", havingValue = "true", matchIfMissing = true)
public class SwaggerConfig {
}

