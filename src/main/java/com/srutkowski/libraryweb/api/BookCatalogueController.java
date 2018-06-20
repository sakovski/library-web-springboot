package com.srutkowski.libraryweb.api;

import com.srutkowski.libraryweb.book.Book;
import com.srutkowski.libraryweb.book.BookRepository;
import com.srutkowski.libraryweb.rent.Rent;
import com.srutkowski.libraryweb.rent.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookCatalogueController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable("id") Long id) {
        return bookRepository.findById(id).get();
    }
}
