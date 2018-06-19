package com.srutkowski.libraryweb.converters;

import com.srutkowski.libraryweb.book.Book;
import com.srutkowski.libraryweb.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToBook implements Converter<String, Book> {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book convert(String s) {
        Long id = new Long(s);
        return bookRepository.findById(id).get();
    }
}
