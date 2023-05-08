package com.example.project.sa2.springboot.controller;

import com.example.project.sa2.springboot.entity.BookDb;
import com.example.project.sa2.springboot.services.DatabaseServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/librarySystem")
public class ConsumerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerController.class);

    @Autowired
    private DatabaseServices databaseServices;

    public ConsumerController(DatabaseServices databaseServices) {
        this.databaseServices = databaseServices;
    }

//     http://localhost:8081/librarySystem/list
    @GetMapping("/list")
    public List<BookDb> getAllBooks(){
        LOGGER.info(String.format("Request of GET all books is received successfully!"));
        return databaseServices.selectAllBooks();
    }

    //     http://localhost:8081/librarySystem/book/1
    @GetMapping("/book/{id}")
    public BookDb getBook(@PathVariable("id") int id){
        LOGGER.info(String.format("Request of GET a book is received successfully!"));
        return databaseServices.selectOneBook(id);
    }
}
