package com.project.lms.LibraryManagementSystem.repository;

import com.project.lms.LibraryManagementSystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

//BookRepository is an interface that extends JpaRepository
public interface BookRepository extends JpaRepository<Book, Long> {

    //additional query methods defined here if needed
}
