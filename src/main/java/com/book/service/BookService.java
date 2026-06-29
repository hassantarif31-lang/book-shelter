package com.book.service;

import com.book.model.*;
import com.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // 添加图书
    public BookModel addBook(BookModel book) {
        return bookRepository.save(book);
    }

    // 删除图书
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    // 查询所有图书
    public List<BookModel> getAllBooks() {
        return bookRepository.findAll();
    }

    // 按ID查询图书
    public Optional<BookModel> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    // 按标题搜索
    public List<BookModel> searchBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    // 按作者搜索
    public List<BookModel> searchBooksByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }

    // 更新图书
    public BookModel updateBook(Long id, BookModel bookDetails) {
        BookModel book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("图书不存在: " + id));
        
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setIsbn(bookDetails.getIsbn());
        book.setYear(bookDetails.getYear());
        book.setPublisher(bookDetails.getPublisher());
        book.setAvailable(bookDetails.getAvailable());
        
        return bookRepository.save(book);
    }

    // 更新图书可用状态
    public BookModel updateAvailability(Long id, Boolean available) {
        BookModel book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("图书不存在: " + id));
        
        book.setAvailable(available);
        return bookRepository.save(book);
    }
}