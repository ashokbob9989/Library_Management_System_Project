package com.project.lms.LibraryManagementSystem.repository;

import com.project.lms.LibraryManagementSystem.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository <Author, Long> {
    @Query("SELECT a FROM Author a WHERE a.personId.id = :id AND a.personId.name = :name")
    Author findAuthorByIdAndName(Long id, String name);

}
