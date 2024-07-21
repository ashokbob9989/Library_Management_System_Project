package com.project.lms.LibraryManagementSystem.repository;

import com.project.lms.LibraryManagementSystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//BookRepository is an interface that extends JpaRepository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByName(String name);

    List<Book> findByPrice(Long price);

    List<Book> findAllByName(String name);

    Book getBookById(Long id);
    //additional query methods defined here if needed
}
