package com.project.lms.LibraryManagementSystem;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info=@Info(
                title="Library Management System",
                description = "Doing CRUD operations in Library Management System",
                summary = "Swagger API Documentation for Library Management System",
                version = "1.0.0",
                termsOfService = "http://swagger.io/terms/",
                contact = @Contact(
                        name = "Guguloth Ashok",
                        email = "Onlycoding143@gmail.com"
                ),
                license = @License(
                        name = "LMS License 1.0.0"
                )
        ),
        servers = @Server(
                description = "Dev",
                url = "http://localhost:8080"
        )
)
@SecurityScheme(
        name = "bookAuth",
        description = "Book Authentication",
        type = SecuritySchemeType.APIKEY,
        in = SecuritySchemeIn.HEADER,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SwaggerConfig {
}
