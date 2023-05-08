package com.example.project.sa2.springboot.services;

import com.example.project.sa2.springboot.entity.BookDb;
import com.example.project.sa2.springboot.model.Book;
import com.example.project.sa2.springboot.repository.BookRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DatabaseServices {

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseServices.class);

    @Autowired
    private BookRepo bookRepo;

    public DatabaseServices(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    public void insertBook(Book book){

        BookDb bookDb = new BookDb();
        bookDb.setAuthor(book.getAuthor());
        bookDb.setCategory(book.getCategory());
        bookDb.setTitle(book.getTitle());
        bookDb.setPrice(book.getPrice());

        bookRepo.save(bookDb);

        LOGGER.info(String.format("New book is inserted successfully in database! -> %s", bookDb.toString()));
    }

    public List<BookDb> selectAllBooks(){
        LOGGER.info(String.format("All books are selected successfully from database!"));
        return bookRepo.findAll();
    }

    public BookDb selectOneBook(int id){
        Optional<BookDb> bookDb = bookRepo.findById(id);
        LOGGER.info(String.format("Book is selected successfully from database! id -> %d", id));
        return bookDb.orElse(null);
    }

    public void deleteBook(int id){
        LOGGER.info(String.format("Book is deleted successfully from database! id -> %d", id));
        bookRepo.deleteById(id);
    }

    public BookDb updateBook(Book newBook){
        Optional<BookDb> oldBook = bookRepo.findById(newBook.getId());
        if (oldBook.isPresent()){
            BookDb book = oldBook.get();
            book.setId(newBook.getId());
            book.setAuthor(newBook.getAuthor());
            book.setCategory(newBook.getCategory());
            book.setTitle(newBook.getTitle());
            book.setPrice(newBook.getPrice());

            LOGGER.info(String.format("Book is Updated successfully in database! -> %s", book.toString()));
            return bookRepo.save(book);
        }else {

            LOGGER.info(String.format("Book does not exist in database!"));
            return null;
        }
    }
}
