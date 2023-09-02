package com.devilkim.app.name

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class NameConfig {

    @Bean
    fun openAPI(): OpenAPI {
        return OpenAPI()
                .info(
                        Info().title("Sandbox API")
                                .description("")
                                .version("v0.0.1")
                )
    }

}