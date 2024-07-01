package com.project.lms.LibraryManagementSystem.repository;

import com.project.lms.LibraryManagementSystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

//BookRepository is an interface that extends JpaRepository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByNameContainingIgnoreCase(String name);

    List<Book> findByAuthorContainingIgnoreCase(String author);

    List<Book> findByPublisherContainingIgnoreCase(String publisher);

    List<Book> findByPrice(Long price);

    List<Book> findByCreatedOn(LocalDateTime createdOn);

    List<Book> findByUpdatedOn(LocalDateTime updatedOn);

    //additional query methods defined here if needed
}
