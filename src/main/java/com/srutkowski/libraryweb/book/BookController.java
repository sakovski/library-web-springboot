package com.srutkowski.libraryweb.book;

import com.srutkowski.libraryweb.search.SearchInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
        model.addAttribute("books", bookRepository.findAll(new Sort(Sort.Direction.ASC, "title")));
        model.addAttribute("search_param", new SearchInput());
        return "book/index";
    }

    @RequestMapping(value="/save", method = RequestMethod.GET)
    public String saveForm(Model model) {
        model.addAttribute("book", new Book());
        return "book/save";
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    public String save(Model model, Book book) {
        bookRepository.save(book);
        return "redirect:/book/index";
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public String delete(Model model, @PathVariable(required = true, name = "id") Long id) {
        Book book = bookRepository.getOne(id);
        if(!book.isRented())
        {
            bookRepository.deleteById(id);
            return "redirect:/book/index";
        }
        return "error/error";
    }

    @RequestMapping(value = "/findOne", method = RequestMethod.GET)
    @ResponseBody
    public Book findOne(Long id) {
        return bookRepository.findById(id).get();
    }

    @PostMapping(value = "/search")
    public String search(Model model, @ModelAttribute SearchInput search_param) {
        System.out.println("SEARCH PARAM:" + search_param);
        List<Book> filteredBook = bookRepository.findAll(new Sort(Sort.Direction.ASC, "title"))
                .stream()
                .filter(book -> book.getTitle().contains(search_param.getUserInput()) || book.getAuthor().contains(search_param.getUserInput()) || book.getIsbnNumber().contains(search_param.getUserInput()))
                .collect(Collectors.toList());
        model.addAttribute("books", filteredBook);
        model.addAttribute("search_param", new SearchInput());
        return "book/index";
    }
}
