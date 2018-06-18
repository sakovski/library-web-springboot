package com.srutkowski.libraryweb.rent;

import com.srutkowski.libraryweb.book.Book;
import com.srutkowski.libraryweb.library_user.LibraryUser;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "rent")
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "library_user_id")
    private LibraryUser libraryUser;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_rented")
    private Date dateRented;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_returned")
    private Date dateReturned;

    public Rent(@NotNull LibraryUser libraryUser, @NotNull Book book, @NotNull Date dateRented, Date dateReturned) {
        this.libraryUser = libraryUser;
        this.book = book;
        this.dateRented = dateRented;
        this.dateReturned = dateReturned;
    }
}
