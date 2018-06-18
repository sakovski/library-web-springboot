package com.srutkowski.libraryweb.library_user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("library_user")
public class LibraryUserController {

    @Autowired
    private LibraryUserRepository libraryUserRepository;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("users", libraryUserRepository.findAll());
        return "library_user/index";
    }

    @RequestMapping(value={"/save","/save/{id}"}, method = RequestMethod.GET)
    public String saveForm(Model model, @PathVariable(required = false, name = "id") Long id) {
        if (null != id) {
            model.addAttribute("user", libraryUserRepository.findById(id));
        } else {
            model.addAttribute("user", new LibraryUser());
        }
        return "library_user/save";
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    public String save(Model model, LibraryUser libraryUser) {
        libraryUserRepository.save(libraryUser);
        return "redirect:/library_user/index";
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public String delete(Model model, @PathVariable(required = true, name = "id") Long id) {
        libraryUserRepository.deleteById(id);
        return "redirect:/library_user/index";
    }

    @RequestMapping(value = "/findOne", method = RequestMethod.GET)
    @ResponseBody
    public LibraryUser findOne(Long id) {
        return libraryUserRepository.findById(id).get();
    }
}
