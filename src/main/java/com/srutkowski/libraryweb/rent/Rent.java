package com.srutkowski.libraryweb.rent;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.srutkowski.libraryweb.book.Book;
import com.srutkowski.libraryweb.library_user.LibraryUser;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "rent")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "library_user_id")
    //@JsonManagedReference
    private LibraryUser libraryUser;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    //@JsonManagedReference
    private Book book;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_rented")
    private Date dateRented;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_returned")
    private Date dateReturned;

    public Rent() {

    }


    public Rent(@NotNull LibraryUser libraryUser, @NotNull Book book, @NotNull Date dateRented) {
        this.libraryUser = libraryUser;
        this.book = book;
        this.dateRented = dateRented;
        this.dateReturned = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LibraryUser getLibraryUser() {
        return libraryUser;
    }

    public void setLibraryUser(LibraryUser libraryUser) {
        this.libraryUser = libraryUser;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getDateRented() {
        return dateRented;
    }

    public void setDateRented(Date dateRented) {
        this.dateRented = dateRented;
    }

    public Date getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(Date dateReturned) {
        this.dateReturned = dateReturned;
    }
}
