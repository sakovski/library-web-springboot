package com.srutkowski.libraryweb.library_user;

import com.srutkowski.libraryweb.rent.Rent;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name="library_user")
public class LibraryUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "firstname")
    private String firstname;

    @NotNull
    @Column(name = "lastname")
    private String lastname;

    @OneToMany(mappedBy = "libraryUser", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Rent> rents;

    public LibraryUser() {

    }


    public LibraryUser(@NotNull String firstname, @NotNull String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.rents = new HashSet<>();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setRents(Set<Rent> rents){
        this.rents = rents;
    }

    public Set<Rent> getRents(){
        return this.rents;
    }

    public Set<Rent> getCurrentRents() {
        return this.rents.stream()
                .filter(rent -> rent.getDateReturned() == null)
                .collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return this.firstname + " " + this.lastname;
    }
}
