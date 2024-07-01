package com.project.lms.LibraryManagementSystem.controller;

import com.project.lms.LibraryManagementSystem.model.Book;
import com.project.lms.LibraryManagementSystem.service.BookService;
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

    @PostMapping("/addBook")
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
    @PostMapping("/addAllBooks")
    public ResponseEntity<List<Book>> addAllBooks(@RequestBody List<Book> books) {
        log.info("Books added successfully");
        return new ResponseEntity<>(this.bookService.addAllBooks(books),HttpStatus.CREATED);
    }

    @GetMapping("/allBooks")
    public ResponseEntity<List<Book>> getAllBooks() {
        log.info("All Books fetched successfully");
        return new ResponseEntity<>(this.bookService.getAllBooks(),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(@RequestParam(required = false) Long id, @RequestParam(required = false) String name, @RequestParam(required = false) String author) {
        List<Book> books=this.bookService.getAllBooks().stream().filter(book -> (id == null || book.getId().equals(id)) && (name == null || book.getName().equals(name)) && (author == null || book.getAuthor().equals(author))).toList();
        log.info("Book with id {} or name {} or author {} fetched successfully", id, name, author);
        return new ResponseEntity<>(books,HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        log.info("Book with id {} fetched successfully", id);
        return new ResponseEntity<>(this.bookService.getBookById(id),HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Book>> getBookByName(@PathVariable String name) {
        log.info("Book with name {} fetched successfully", name);
        return new ResponseEntity<>(this.bookService.getBookByName(name),HttpStatus.OK);
    }

    @GetMapping("/author/{author}")
    public ResponseEntity<List<Book>> getBookByAuthor(@PathVariable String author) {
        log.info("Book with author {} fetched successfully", author);
        return new ResponseEntity<>(this.bookService.getBookByAuthor(author),HttpStatus.OK);
    }

    @GetMapping("/publisher/{publisher}")
    public ResponseEntity<List<Book>> getBookByPublisher(@PathVariable String publisher) {
        log.info("Book with publisher {} fetched successfully", publisher);
        return new ResponseEntity<>(this.bookService.getBookByPublisher(publisher),HttpStatus.OK);
    }

    @GetMapping("/price/{price}")
    public ResponseEntity<List<Book>> getBookByPrice(@PathVariable Long price) {
        log.info("Book with price {} fetched successfully", price);
        return new ResponseEntity<>(this.bookService.getBookByPrice(price),HttpStatus.OK);
    }

    //todo -> get data by created on date/month/year or any other combinations.
    @GetMapping("/createdOn/{createdOn}")
    public ResponseEntity<List<Book>> getBookByCreatedOn(@PathVariable LocalDateTime createdOn) {
        log.info("Book with created on {} fetched successfully", createdOn);
        return new ResponseEntity<>(this.bookService.getBookByCreatedOn(createdOn),HttpStatus.OK);
    }

    //todo -> get data by updated on date/month/year or any other combinations.
    @GetMapping("/updatedOn/{updatedOn}")
    public ResponseEntity<List<Book>> getBookByUpdatedOn(@PathVariable LocalDateTime updatedOn) {
        log.info("Book with updated on {} fetched successfully", updatedOn);
        return new ResponseEntity<>(this.bookService.getBookByUpdatedOn(updatedOn),HttpStatus.OK);
    }

    @PutMapping("/updateBookById/{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable Long id, @RequestBody Book book) {
        log.info("Book with id {} updated successfully", id);
        return new ResponseEntity<>(this.bookService.updateBookById(id,book),HttpStatus.OK);
    }

    @PatchMapping("/minorUpdateBookById/{id}")
    public ResponseEntity<Book> minorUpdateBookById(@PathVariable Long id, @RequestBody Book book) {
        log.info("Updated Book with minor changes in id {} successfully", id);
        return new ResponseEntity<>(this.bookService.minorUpdateBookById(id,book),HttpStatus.OK);
    }

    @DeleteMapping("/deleteBookById/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable Long id) {
        log.info("Book with id {} deleted successfully", id);
        return new ResponseEntity<>(this.bookService.deleteBookById(id),HttpStatus.OK);
    }

    @DeleteMapping("/deleteAllBooks")
    public ResponseEntity<String> deleteAllBooks() {
        log.info("All Books deleted successfully");
        return new ResponseEntity<>(this.bookService.deleteAllBooks(),HttpStatus.OK);
    }

    @DeleteMapping("/deleteBookByName/{name}")
    public ResponseEntity<String> deleteBookByName(@PathVariable String name) {
        log.info("Book with name {} deleted successfully", name);
        return new ResponseEntity<>(this.bookService.deleteBookByName(name),HttpStatus.OK);
    }

    @DeleteMapping("/deleteBookByAuthor/{author}")
    public ResponseEntity<String> deleteBookByAuthor(@PathVariable String author) {
        log.info("Book with author {} deleted successfully", author);
        return new ResponseEntity<>(this.bookService.deleteBookByAuthor(author),HttpStatus.OK);
    }

    @DeleteMapping("/deleteBookByPublisher/{publisher}")
    public ResponseEntity<String> deleteBookByPublisher(@PathVariable String publisher) {
        log.info("Book with publisher {} deleted successfully", publisher);
        return new ResponseEntity<>(this.bookService.deleteBookByPublisher(publisher),HttpStatus.OK);
    }

    @DeleteMapping("/deleteBookByPrice/{price}")
    public ResponseEntity<String> deleteBookByPrice(@PathVariable Long price) {
        log.info("Book with price {} deleted successfully", price);
        return new ResponseEntity<>(this.bookService.deleteBookByPrice(price),HttpStatus.OK);
    }

    @DeleteMapping("/deleteBookByCreatedOn/{createdOn}")
    public ResponseEntity<String> deleteBookByCreatedOn(@PathVariable LocalDateTime createdOn) {
        log.info("Book with created on {} deleted successfully", createdOn);
        return new ResponseEntity<>(this.bookService.deleteBookByCreatedOn(createdOn),HttpStatus.OK);
    }

    @DeleteMapping("/deleteBookByUpdatedOn/{updatedOn}")
    public ResponseEntity<String> deleteBookByUpdatedOn(@PathVariable LocalDateTime updatedOn) {
        log.info("Book with updated on {} deleted successfully", updatedOn);
        return new ResponseEntity<>(this.bookService.deleteBookByUpdatedOn(updatedOn),HttpStatus.OK);
    }
}
