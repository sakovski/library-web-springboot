package com.srutkowski.libraryweb.rent;

import com.srutkowski.libraryweb.book.Book;
import com.srutkowski.libraryweb.book.BookRepository;
import com.srutkowski.libraryweb.library_user.LibraryUser;
import com.srutkowski.libraryweb.library_user.LibraryUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("rent")
public class RentController {

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private LibraryUserRepository libraryUserRepository;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("rents", rentRepository.findAll());
        return "rent/index";
    }

    @RequestMapping(value={"/save","/save/{id}"}, method = RequestMethod.GET)
    public String saveForm(Model model, @PathVariable(required = false, name = "id") Long id) {
        List<Book> books = bookRepository.findAll().stream()
                .filter(book -> !book.isRented())
                .collect(Collectors.toList());
        List<LibraryUser> libraryUsers = libraryUserRepository.findAll();
        model.addAttribute("books", books);
        model.addAttribute("library_users", libraryUsers);
        if (null != id) {
            model.addAttribute("rent", rentRepository.findById(id));
        } else {
            model.addAttribute("rent", new Rent());
        }
        return "rent/save";
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    public String save(Model model, Rent rent) {
        rent.setDateRented(new Date());
        rent.getBook().setRented(true);
        rentRepository.save(rent);
        return "redirect:/rent/index";
    }

    @RequestMapping(value="/return/{id}", method = RequestMethod.GET)
    public String returnbook(Model model, @PathVariable(required = true, name = "id") Long id) {
        Rent rent = rentRepository.getOne(id);
        rent.setDateReturned(new Date());
        Book book = rent.getBook();
        book.setRented(false);
        bookRepository.save(book);
        rentRepository.save(rent);
        return "redirect:/rent/index";
    }
}
