package com.project.lms.LibraryManagementSystem.controller;

import com.project.lms.LibraryManagementSystem.model.Book;
import com.project.lms.LibraryManagementSystem.service.BookService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    Logger log = LoggerFactory.getLogger(BookController.class);

    @PostMapping(value = "/addBook", produces = "application/json")
    @Operation(summary = "Add Book", description = "Post request to Add Book")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Book added successfully"),
        @ApiResponse(responseCode = "400", description = "Bad Request"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error"),
        @ApiResponse(responseCode = "404", description = "Not Found"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden"),
    })
    @Parameters({
        @Parameter(name="name", required = true, description = "Name of the book"),
        @Parameter(name="author", required = true, description = "Author of the book"),
        @Parameter(name="publisher", required = true, description = "publisher of the book"),
        @Parameter(name="price", required = true, description = "price on of the book"),
    })
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        if(book.getCreatedOn() == null) {
            book.setCreatedOn(LocalDateTime.now());
        }
        if(book.getUpdatedOn() == null) {
            book.setUpdatedOn(LocalDateTime.now());
        }
        log.info("Book added successfully");
        return new ResponseEntity<>(this.bookService.addBook(book),HttpStatus.CREATED);
    }

    @PostMapping(value = "/addAllBooks", produces = "application/json")
    @Operation(summary = "Add All Books", description = "Post request to Add All Books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Books added successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<List<Book>> addAllBooks(@RequestBody List<Book> books) {
        log.info("Books added successfully");
        return new ResponseEntity<>(this.bookService.addAllBooks(books),HttpStatus.CREATED);
    }

    @GetMapping(value = "/allBooks", produces = "application/json")
    @Operation(summary = "Get All Books", description = "Get request to Get All Books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Books fetched successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<List<Book>> getAllBooks() {
        log.info("All Books fetched successfully");
        return new ResponseEntity<>(this.bookService.getAllBooks(),HttpStatus.OK);
    }

    @GetMapping(produces = "application/json")
    @Operation(summary = "Get Books", description = "Get request to Get Books by id, name or author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Books fetched successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })

    public ResponseEntity<List<Book>> getAllBooks(@RequestParam(required = false) Long id, @RequestParam(required = false) String name, @RequestParam(required = false) String author) {
        List<Book> books=this.bookService.getAllBooks().stream().filter(book -> (id == null || book.getId().equals(id)) && (name == null || book.getName().equals(name)) && (author == null || book.getAuthor().equals(author))).toList();
        log.info("Book with id {} or name {} or author {} fetched successfully", id, name, author);
        return new ResponseEntity<>(books,HttpStatus.OK);
    }

    @GetMapping(value = "/id/{id}", produces = "application/json")
    @Operation(summary = "Get Book", description = "Get request to Get Book by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book fetched successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @Parameters({
            @Parameter(name="id", required = true, description = "Id of the book")
    })
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        log.info("Book with id {} fetched successfully", id);
        return new ResponseEntity<>(this.bookService.getBookById(id),HttpStatus.OK);
    }

    @GetMapping(value = "/name/{name}", produces = "application/json")
    @Operation(summary = "Get Book", description = "Get request to Get Book by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book fetched successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @Parameters({
            @Parameter(name="name", required = true, description = "Name of the book")
    })
    public ResponseEntity<List<Book>> getBookByName(@PathVariable String name) {
        log.info("Book with name {} fetched successfully", name);
        return new ResponseEntity<>(this.bookService.getBookByName(name),HttpStatus.OK);
    }

    @GetMapping(value = "/author/{author}", produces = "application/json")
    @Operation(summary = "Get Book", description = "Get request to Get Book by author")
    @Parameters({
            @Parameter(name="author", required = true, description = "Author of the book")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book fetched successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<List<Book>> getBookByAuthor(@PathVariable String author) {
        log.info("Book with author {} fetched successfully", author);
        return new ResponseEntity<>(this.bookService.getBookByAuthor(author),HttpStatus.OK);
    }

    @GetMapping(value = "/publisher/{publisher}", produces = "application/json")
    @Operation(summary = "Get Book", description = "Get request to Get Book by publisher")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book fetched successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @Parameters({
            @Parameter(name="publisher", required = true, description = "Publisher of the book")
    })
    public ResponseEntity<List<Book>> getBookByPublisher(@PathVariable String publisher) {
        log.info("Book with publisher {} fetched successfully", publisher);
        return new ResponseEntity<>(this.bookService.getBookByPublisher(publisher),HttpStatus.OK);
    }

    @GetMapping(value = "/price/{price}", produces = "application/json")
    @Operation(summary = "Get Book", description = "Get request to Get Book by price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book fetched successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @Parameters({
            @Parameter(name="price", required = true, description = "Price of the book")
    })
    public ResponseEntity<List<Book>> getBookByPrice(@PathVariable Long price) {
        log.info("Book with price {} fetched successfully", price);
        return new ResponseEntity<>(this.bookService.getBookByPrice(price),HttpStatus.OK);
    }

    @GetMapping(value = "/createdOn/{createdOn}", produces = "application/json")
    @Operation(summary = "Get Book", description = "Get request to Get Book by created on")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book fetched successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @Parameters({
            @Parameter(name="createdOn", required = true, description = "Created on of the book")
    })
    public ResponseEntity<List<Book>> getBookByCreatedOn(@PathVariable LocalDateTime createdOn) {
        log.info("Book with created on {} fetched successfully", createdOn);
        return new ResponseEntity<>(this.bookService.getBookByCreatedOn(createdOn),HttpStatus.OK);
    }

    @GetMapping(value = "/updatedOn/{updatedOn}", produces = "application/json")
    @Operation(summary = "Get Book", description = "Get request to Get Book by updated on")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book fetched successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @Parameters({
            @Parameter(name="updatedOn", required = true, description = "Updated on of the book")
    })
    public ResponseEntity<List<Book>> getBookByUpdatedOn(@PathVariable LocalDateTime updatedOn) {
        log.info("Book with updated on {} fetched successfully", updatedOn);
        return new ResponseEntity<>(this.bookService.getBookByUpdatedOn(updatedOn),HttpStatus.OK);
    }

    @PutMapping(value = "/updateBookById/{id}", produces = "application/json")
    @Operation(summary = "Update Book", description = "Put request to Update Book by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @Parameters({
            @Parameter(name="id", required = true, description = "Id of the book")
    })
    public ResponseEntity<Book> updateBookById(@PathVariable Long id, @RequestBody Book book) {
        log.info("Book with id {} updated successfully", id);
        return new ResponseEntity<>(this.bookService.updateBookById(id,book),HttpStatus.OK);
    }

    @PatchMapping(value = "/minorUpdateBookById/{id}", produces = "application/json")
    @Operation(summary = "Update Book", description = "Patch request to Update Book by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @Parameters({
            @Parameter(name="id", required = true, description = "Id of the book")
    })
    public ResponseEntity<Book> minorUpdateBookById(@PathVariable Long id, @RequestBody Book book) {
        log.info("Updated Book with minor changes in id {} successfully", id);
        return new ResponseEntity<>(this.bookService.minorUpdateBookById(id,book),HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteBookById/{id}", produces = "application/json")
    @Operation(summary = "Delete Book", description = "Delete request to Delete Book by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @Parameters({
            @Parameter(name="id", required = true, description = "Id of the book")
    })
    public ResponseEntity<String> deleteBookById(@PathVariable Long id) {
        log.info("Book with id {} deleted successfully", id);
        return new ResponseEntity<>(this.bookService.deleteBookById(id),HttpStatus.OK);
    }

    //to hide this api call from user
    @Hidden
    @DeleteMapping(value = "/deleteAllBooks", produces = "application/json")
    @Operation(summary = "Delete All Books", description = "Delete request to Delete All Books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Books deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @Parameters({
            @Parameter(name="id", required = true, description = "Id of the book")
    })
    public ResponseEntity<String> deleteAllBooks() {
        log.info("All Books deleted successfully");
        return new ResponseEntity<>(this.bookService.deleteAllBooks(),HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteBookByName/{name}", produces = "application/json")
    @Operation(summary = "Delete Book", description = "Delete request to Delete Book by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @Parameters({
            @Parameter(name="name", required = true, description = "Name of the book")
    })
    public ResponseEntity<String> deleteBookByName(@PathVariable String name) {
        log.info("Book with name {} deleted successfully", name);
        return new ResponseEntity<>(this.bookService.deleteBookByName(name),HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteBookByAuthor/{author}", produces = "application/json")
    @Operation(summary = "Delete Book", description = "Delete request to Delete Book by author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @Parameters({
            @Parameter(name="author", required = true, description = "Author of the book")
    })
    public ResponseEntity<String> deleteBookByAuthor(@PathVariable String author) {
        log.info("Book with author {} deleted successfully", author);
        return new ResponseEntity<>(this.bookService.deleteBookByAuthor(author),HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteBookByPublisher/{publisher}", produces = "application/json")
    @Operation(summary = "Delete Book", description = "Delete request to Delete Book by publisher")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @Parameters({
            @Parameter(name="publisher", required = true, description = "Publisher of the book")
    })
    public ResponseEntity<String> deleteBookByPublisher(@PathVariable String publisher) {
        log.info("Book with publisher {} deleted successfully", publisher);
        return new ResponseEntity<>(this.bookService.deleteBookByPublisher(publisher),HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteBookByPrice/{price}", produces = "application/json")
    @Operation(summary = "Delete Book", description = "Delete request to Delete Book by price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @Parameters({
            @Parameter(name="price", required = true, description = "Price of the book")
    })
    public ResponseEntity<String> deleteBookByPrice(@PathVariable Long price) {
        log.info("Book with price {} deleted successfully", price);
        return new ResponseEntity<>(this.bookService.deleteBookByPrice(price),HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteBookByCreatedOn/{createdOn}", produces = "application/json")
    @Operation(summary = "Delete Book", description = "Delete request to Delete Book by created on")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @Parameters({
            @Parameter(name="createdOn", required = true, description = "Created on of the book")
    })
    public ResponseEntity<String> deleteBookByCreatedOn(@PathVariable LocalDateTime createdOn) {
        log.info("Book with created on {} deleted successfully", createdOn);
        return new ResponseEntity<>(this.bookService.deleteBookByCreatedOn(createdOn),HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteBookByUpdatedOn/{updatedOn}", produces =  "application/json")
    @Operation(summary = "Delete Book", description = "Delete request to Delete Book by updated on")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @Parameters({
            @Parameter(name="updatedOn", required = true, description = "Updated on of the book")
    })
    public ResponseEntity<String> deleteBookByUpdatedOn(@PathVariable LocalDateTime updatedOn) {
        log.info("Book with updated on {} deleted successfully", updatedOn);
        return new ResponseEntity<>(this.bookService.deleteBookByUpdatedOn(updatedOn),HttpStatus.OK);
    }
}
