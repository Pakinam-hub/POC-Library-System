package com.example.project.sa2.springboot.repository;

import com.example.project.sa2.springboot.entity.BookDb;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<BookDb, Integer> {
}
