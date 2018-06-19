package com.srutkowski.libraryweb.converters;

import com.srutkowski.libraryweb.library_user.LibraryUser;
import com.srutkowski.libraryweb.library_user.LibraryUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToLibraryUser implements Converter<String, LibraryUser> {

    @Autowired
    private LibraryUserRepository libraryUserRepository;


    @Override
    public LibraryUser convert(String s) {
        Long id = new Long(s);
        return libraryUserRepository.findById(id).get();
    }
}
