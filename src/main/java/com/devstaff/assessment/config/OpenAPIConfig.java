package com.devstaff.assessment.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI openApiConfigs() {
        return new OpenAPI()
                .info(new Info().title("FarmCollector")
                        .description("API documentation for FarmCollector App")
                        .version("v1.0"));
    }
}
