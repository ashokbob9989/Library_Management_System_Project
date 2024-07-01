package com.project.lms.LibraryManagementSystem.service;

import com.project.lms.LibraryManagementSystem.model.Book;
import com.project.lms.LibraryManagementSystem.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book addBook(Book book) {
        return this.bookRepository.save(book);
    }

    public List<Book> addAllBooks(List<Book> books) {
        return this.bookRepository.saveAll(books);
    }

    public List<Book> getAllBooks() {
        return this.bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return this.bookRepository.findById(id).orElse(null);
    }

    public List<Book> getBookByName(String name) {
        return this.bookRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Book> getBookByAuthor(String author) {
        return this.bookRepository.findByAuthorContainingIgnoreCase(author);
    }

    public List<Book> getBookByPublisher(String publisher) {
        return this.bookRepository.findByPublisherContainingIgnoreCase(publisher);
    }

    public List<Book> getBookByPrice(Long price) {
        return this.bookRepository.findByPrice(price);
    }

    public List<Book> getBookByCreatedOn(LocalDateTime createdOn) {
        return this.bookRepository.findByCreatedOn(createdOn);
    }

    public List<Book> getBookByUpdatedOn(LocalDateTime updatedOn) {
        return this.bookRepository.findByUpdatedOn(updatedOn);
    }

    //major update happened so we use PUT method
    public Book updateBookById(Long id, Book book) {
        Book existingBook = this.bookRepository.findById(id).orElse(null);
        assert existingBook != null;
        existingBook.setName(book.getName());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setPublisher(book.getPublisher());
        existingBook.setPrice(book.getPrice());
        existingBook.setUpdatedOn(LocalDateTime.now());
        return this.bookRepository.save(existingBook);
    }

    //minor update happened rest all remains same, so we use PATCH method
    public Book minorUpdateBookById(Long id, Book book) {
        Book existingBook = this.bookRepository.findById(id).orElse(null);
        assert existingBook != null;
        existingBook.setPrice(book.getPrice());
        existingBook.setUpdatedOn(LocalDateTime.now());
        return this.bookRepository.save(existingBook);
    }

    public String deleteBookById(Long id) {
        this.bookRepository.deleteById(id);
        return "Book deleted successfully";
    }
    public String deleteAllBooks() {
        this.bookRepository.deleteAll();
        return "All books deleted successfully";
    }

    public String deleteBookByName(String name) {
        List<Book> booksToDelete = this.bookRepository.findByNameContainingIgnoreCase(name);
        if (booksToDelete.isEmpty()) {
            return "No book found with name " + name;
        }
        this.bookRepository.deleteAll(booksToDelete);
        return "Book(s) deleted successfully";
    }

    public String deleteBookByAuthor(String author) {
        List<Book> booksToDelete = this.bookRepository.findByAuthorContainingIgnoreCase(author);
        if (booksToDelete.isEmpty()) {
            return "No book found with author " + author;
        }
        this.bookRepository.deleteAll(booksToDelete);
        return "Book(s) deleted successfully";
    }

    public String deleteBookByPublisher(String publisher) {
        List<Book> booksToDelete = this.bookRepository.findByPublisherContainingIgnoreCase(publisher);
        if (booksToDelete.isEmpty()) {
            return "No book found with publisher " + publisher;
        }
        this.bookRepository.deleteAll(booksToDelete);
        return "Book(s) deleted successfully";
    }

    public String deleteBookByPrice(Long price) {
        List<Book> booksToDelete = this.bookRepository.findByPrice(price);
        if (booksToDelete.isEmpty()) {
            return "No book found with price " + price;
        }
        this.bookRepository.deleteAll(booksToDelete);
        return "Book(s) deleted successfully";
    }

    public String deleteBookByCreatedOn(LocalDateTime createdOn) {
        List<Book> booksToDelete = this.bookRepository.findByCreatedOn(createdOn);
        if (booksToDelete.isEmpty()) {
            return "No book found with createdOn " + createdOn;
        }
        this.bookRepository.deleteAll(booksToDelete);
        return "Book(s) deleted successfully";
    }

    public String deleteBookByUpdatedOn(LocalDateTime updatedOn) {
        List<Book> booksToDelete = this.bookRepository.findByUpdatedOn(updatedOn);
        if (booksToDelete.isEmpty()) {
            return "No book found with updatedOn " + updatedOn;
        }
        this.bookRepository.deleteAll(booksToDelete);
        return "Book(s) deleted successfully";
    }
}
