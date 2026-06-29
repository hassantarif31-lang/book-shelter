package com.book.repository;


import com.book.model.BookModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookModel, Long> {
    
    List<BookModel> findByTitleContainingIgnoreCase(String title);
    
    List<BookModel> findByAuthorContainingIgnoreCase(String author);
    
    List<BookModel> findByIsbn(String isbn);
}