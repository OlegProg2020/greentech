package org.greentech.backend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "${spring.application.name}",
        version = "1.0.0")
)
@SecurityScheme(
        name = "Bearer Authentication",
        scheme = "bearer",
        bearerFormat = "JWT",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER,
        description = "Требуется получить токен, используя /login или /register и посылать его " +
                "в каждом запросе в заголовках. Пример: заголовок - Authorization, значение - Bearer your-token. " +
                "Токен действует 1 час."
)
public class OpenApiSpecificationConfig {
}
