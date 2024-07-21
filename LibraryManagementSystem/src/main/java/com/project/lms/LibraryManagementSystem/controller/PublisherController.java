package com.project.lms.LibraryManagementSystem.controller;

import com.project.lms.LibraryManagementSystem.service.PublisherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/publisher")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    Logger log = LoggerFactory.getLogger(PublisherController.class);

}
