package com.example.project.sa2.springboot.services;

import com.example.project.sa2.springboot.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerServices {

    private final String topicName = "${topic.name}";
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerServices.class);

    @Autowired
    private DatabaseServices databaseServices;

    public ConsumerServices(DatabaseServices databaseServices) {
        this.databaseServices = databaseServices;
    }


    @KafkaListener(topics = topicName, groupId = "customergroup")
    public void consumeBook(Book book){

        if (book.getOperationType().equals("post")){
            databaseServices.insertBook(book);
            LOGGER.info(String.format("New book is received successfully from the kafka topic! -> %s", book.toString()));

        } else if (book.getOperationType().equals("delete")) {
            databaseServices.deleteBook(book.getId());
            LOGGER.info(String.format("Deleted book is received successfully from the kafka topic! -> %s", book.toString()));
        }else if (book.getOperationType().equals("update")) {
            databaseServices.updateBook(book);
            LOGGER.info(String.format("Updated book is received successfully from the kafka topic! -> %s", book.toString()));
        }
    }
}
