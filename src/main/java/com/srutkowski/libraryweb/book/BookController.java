package com.srutkowski.libraryweb.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("book")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("books", bookRepository.findAll());
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
        //model.addAttribute("books", bookRepository.findAll());
        //return "book/index";
        return "redirect:/book/index";
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public String delete(Model model, @PathVariable(required = true, name = "id") Long id) {
        bookRepository.deleteById(id);
        model.addAttribute("books", bookRepository.findAll());
        return "book/index";
    }

    @RequestMapping(value = "/findOne", method = RequestMethod.GET)
    @ResponseBody
    public Book findOne(Long id) {
        return bookRepository.findById(id).get();
    }


}
