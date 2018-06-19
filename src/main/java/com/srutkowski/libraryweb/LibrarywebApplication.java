package com.srutkowski.libraryweb;

import com.srutkowski.libraryweb.book.Book;
import com.srutkowski.libraryweb.book.BookRepository;
import com.srutkowski.libraryweb.library_user.LibraryUser;
import com.srutkowski.libraryweb.library_user.LibraryUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {ErrorMvcAutoConfiguration.class})
public class LibrarywebApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibrarywebApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(LibraryUserRepository libraryUserRepository, BookRepository bookRepository) {
        return args -> {
            bookRepository.save(new Book("Harry Potter And The Chamber Of Secret", "J.K.Rowling", "0439064872", false));
            bookRepository.save(new Book("Harry Potter And The Chamber Of Secret", "J.K.Rowling", "0439064872", false));
            bookRepository.save(new Book("Inferno", "Dan Brown", "2354678545", false));
            bookRepository.save(new Book("Inferno", "Dan Brown", "1446323452", false));
            bookRepository.save(new Book("Da Vinci Code", "Dan Brown", "111133333", false));
            libraryUserRepository.save(new LibraryUser("John", "Smith"));
            libraryUserRepository.save(new LibraryUser("Mark", "Allison"));
        };
    }
}
