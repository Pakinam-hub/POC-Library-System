package com.example.project.sa2.springboot.service;

import com.example.project.sa2.springboot.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    @Value("${topic.name}")
    private String topicName;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerService.class);

    @Autowired
    private KafkaTemplate<String, Book> kafkaTemplate;

    public ProducerService(KafkaTemplate<String, Book> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Book book){
        Message<Book> message = MessageBuilder.withPayload(book).setHeader(KafkaHeaders.TOPIC, topicName).build();

        kafkaTemplate.send(message);

        LOGGER.info(String.format("Book message is sent successfully to the kafka topic! -> %s", book.toString()));
    }
}
