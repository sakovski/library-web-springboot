package com.srutkowski.libraryweb.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("book")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        model.addAttribute("search_param", "");
        return "book/index";
    }

    @RequestMapping(value={"/save","/save/{id}"}, method = RequestMethod.GET)
    public String saveForm(Model model, @PathVariable(required = false, name = "id") Long id) {
        if (null != id) {
            model.addAttribute("book", bookRepository.findById(id));
        } else {
            model.addAttribute("book", new Book());
        }
        return "book/save";
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    public String save(Model model, Book book) {
        bookRepository.save(book);
        return "redirect:/book/index";
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public String delete(Model model, @PathVariable(required = true, name = "id") Long id) {
        bookRepository.deleteById(id);
        return "redirect:/book/index";
    }

    @RequestMapping(value = "/findOne", method = RequestMethod.GET)
    @ResponseBody
    public Book findOne(Long id) {
        return bookRepository.findById(id).get();
    }

    @RequestMapping(value="/search", method = RequestMethod.POST)
    public String rent(Model model, Book book) {
        model.addAttribute("book", book);
        return "redirect:/rent/save";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(Model model, String search_param) {
        List<Book> filteredBook = bookRepository.findAll().stream()
                .filter(book -> book.getTitle().contains(search_param) || book.getAuthor().contains(search_param) || book.getIsbnNumber().contains(search_param))
                .collect(Collectors.toList());
        model.addAttribute("books", filteredBook);
        model.addAttribute("search_param", "");
        return "book/index";
    }
}
