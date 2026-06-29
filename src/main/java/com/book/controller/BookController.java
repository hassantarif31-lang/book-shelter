package com.book.controller;

import com.book.model.*;
import com.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")
public class BookController {

    @Autowired
    private BookService bookService;

    // GET 所有图书
    @GetMapping
    public ResponseEntity<List<BookModel>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    // GET 按ID查询
    @GetMapping("/{id}")
    public ResponseEntity<BookModel> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET 搜索
    @GetMapping("/search")
    public ResponseEntity<List<BookModel>> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author) {
        
        if (title != null && !title.isEmpty()) {
            return ResponseEntity.ok(bookService.searchBooksByTitle(title));
        } else if (author != null && !author.isEmpty()) {
            return ResponseEntity.ok(bookService.searchBooksByAuthor(author));
        }
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    // POST 添加图书
    @PostMapping
    public ResponseEntity<BookModel> addBook(@RequestBody BookModel book) {
        BookModel savedBook = bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    // PUT 更新图书
    @PutMapping("/{id}")
    public ResponseEntity<BookModel> updateBook(@PathVariable Long id, @RequestBody BookModel book) {
        try {
            BookModel updatedBook = bookService.updateBook(id, book);
            return ResponseEntity.ok(updatedBook);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // PATCH 更新可用状态
    @PatchMapping("/{id}/availability")
    public ResponseEntity<BookModel> updateAvailability(
            @PathVariable Long id, 
            @RequestParam Boolean available) {
        try {
            BookModel updatedBook = bookService.updateAvailability(id, available);
            return ResponseEntity.ok(updatedBook);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE 删除图书
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}