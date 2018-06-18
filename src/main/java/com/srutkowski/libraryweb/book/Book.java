package com.srutkowski.libraryweb.book;

import com.srutkowski.libraryweb.rent.Rent;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "author")
    private String author;

    @NotNull
    @Column(name = "isbn_number")
    private String isbnNumber;

    @Column(name = "is_rented")
    public boolean isRented;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Rent> rents;

    public Book() {

    }

    public Book(@NotNull String title, @NotNull String author, @NotNull String isbnNumber, boolean isRented) {
        this.title = title;
        this.author = author;
        this.isbnNumber = isbnNumber;
        this.isRented = isRented;
        this.rents = new HashSet<>();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbnNumber() {
        return isbnNumber;
    }

    public void setIsbnNumber(String isbnNumber) {
        this.isbnNumber = isbnNumber;
    }

    public boolean isRented() {
        return isRented;
    }

    public String isAvailable() {
        return isRented ? "No" : "Yes";
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    public void setRents(Set<Rent> rents){
        this.rents = rents;
    }

    public Set<Rent> getRents(){
        return this.rents;
    }
}
