package com.project.lms.LibraryManagementSystem.config;

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
                title="Welcome to Library Management System",
                description = """
                        Library Management System where you can explore a vast collection of books, along with detailed information about their authors, publishers, and more. Our system is designed to make it easy for you to access all the information you need about your favorite books.

                        **Features**:
                        \n**Book Details**: Utilize the Book Controller to access comprehensive details about any book in our library. Whether you're searching by title, genre, author, or any other factor, our system allows you to quickly retrieve the information you're looking for. See the example provided in each API endpoint to understand the required fields and simply hit execute to get the details of the API call.
                        \n**Author Details**: Need more information about a specific author? Our Author Controller is here to help. Easily find details about the authors of your favorite books and explore their other works. If you provide details of an author, those details will automatically be included in the author databases. If you only need to add author details, you can do so in the Author Controller. See the example provided in each API endpoint to understand the required fields and simply hit execute to get the details of the API call.
                        \n**Publisher Details**: Explore information about the publishers behind the books. Use the Publisher Controller to discover more about the publishing houses associated with the books in our library. If you provide details of an publisher, those details will automatically be included in the publisher databases. If you only need to add publisher details, you can do so in the Publisher Controller. See the example provided in each API endpoint to understand the required fields and simply hit execute to get the details of the API call.
                        We are dedicated to providing you with a seamless and enriching library experience. Happy reading!""",
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
