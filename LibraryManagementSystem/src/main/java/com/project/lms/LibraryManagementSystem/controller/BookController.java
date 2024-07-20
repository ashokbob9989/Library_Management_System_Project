package com.project.lms.LibraryManagementSystem.controller;

import com.project.lms.LibraryManagementSystem.model.*;
import com.project.lms.LibraryManagementSystem.service.AuthorService;
import com.project.lms.LibraryManagementSystem.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;

import com.project.lms.LibraryManagementSystem.service.PublisherService;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private PublisherService publisherService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    Logger log = LoggerFactory.getLogger(BookController.class);

    @PostMapping(value = "/addBook", produces = "application/json")
    @Operation(summary = "Add one book at a time", description = "See example request to add book")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Book is created successfully"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "404", description = "Book not found"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    public ResponseEntity<Book> addBook(@RequestBody Book book) {

        if(book.getCreatedOn() == null) {
            book.setCreatedOn(LocalDateTime.now());
        }
        book.setUpdatedOn(LocalDateTime.now());

        if(book.getAuthor()!=null) {
            if(book.getAuthor().getCreatedOn() == null) {
                book.getAuthor().setCreatedOn(LocalDateTime.now());
            }
            book.getAuthor().setUpdatedOn(LocalDateTime.now());

            this.authorService.addAuthor(book.getAuthor());
        }

        if(book.getPublisher()!=null) {
            if(book.getPublisher().getCreatedOn() == null) {
                book.getPublisher().setCreatedOn(LocalDateTime.now());
            }
            book.getPublisher().setUpdatedOn(LocalDateTime.now());

            this.publisherService.addPublisher(book.getPublisher());
        }

        return new ResponseEntity<Book>(this.bookService.addBook(book), HttpStatus.CREATED);
    }

    @GetMapping("/getAllBooks")
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<List<Book>>(this.bookService.getAllBooks(), HttpStatus.OK);
    }
}
