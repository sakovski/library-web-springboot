package com.srutkowski.libraryweb.library_user;

import javax.persistence.Entity;

@Entity

public class LibraryUser {
    private final String firstname;
    private final String lastname;

    public LibraryUser(String firstname, String lastname)
    {
        this.firstname = firstname;
        this.lastname = lastname;
    }


    public String getFirstname() {
        return this.firstname;
    }

    public String getLastname() {
        return this.lastname;
    }
}
