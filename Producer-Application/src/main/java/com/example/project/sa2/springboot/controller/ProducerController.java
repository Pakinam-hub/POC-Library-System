package com.example.project.sa2.springboot.controller;

import com.example.project.sa2.springboot.model.Book;
import com.example.project.sa2.springboot.service.ProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/librarySystem")
public class ProducerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerController.class);

    @Autowired
    private ProducerService producerService;

    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    //     http://localhost:8080/librarySystem/addBook
    @PostMapping("/addBook")
    public ResponseEntity<String> addBook(@RequestBody Book book){
        book.setOperationType("post");
        producerService.sendMessage(book);
        LOGGER.info(String.format("Request of POST a new book is received successfully! -> %s", book.toString()));
        return ResponseEntity.ok("New book is added successfully to library website! " + book.toString());
    }

    //    http://localhost:8080/librarySystem/deleteBook
    @DeleteMapping("/deleteBook")
    public ResponseEntity<String> deleteBook(@RequestBody Book book){
        book.setOperationType("delete");
        producerService.sendMessage(book);
        LOGGER.info(String.format("Request of DELETE a book is received successfully! -> %s", book.toString()));
        return ResponseEntity.ok("A book is deleted successfully from library website! " + book.toString());
    }

    //   http://localhost:8080/librarySystem/updateBook
    @PutMapping("/updateBook")
    public ResponseEntity<String> updateBook(@RequestBody Book book){
        book.setOperationType("update");
        producerService.sendMessage(book);
        LOGGER.info(String.format("Request of PUT an book is received successfully! -> %s", book.toString()));
        return ResponseEntity.ok("An book is updated successfully from library website! " + book.toString());
    }
}
