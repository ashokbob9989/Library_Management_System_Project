package com.project.lms.LibraryManagementSystem.controller;

import com.project.lms.LibraryManagementSystem.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    Logger log = LoggerFactory.getLogger(AuthorController.class);

}
