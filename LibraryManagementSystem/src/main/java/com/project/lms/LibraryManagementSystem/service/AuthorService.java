package com.project.lms.LibraryManagementSystem.service;

import com.project.lms.LibraryManagementSystem.model.Author;
import com.project.lms.LibraryManagementSystem.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public void addAuthor(Author author) {
        authorRepository.save(author);
    }

    public Author findAuthorByIdAndName(Long id, String name) {
        return authorRepository.findAuthorByIdAndName(id, name);
    }
}
