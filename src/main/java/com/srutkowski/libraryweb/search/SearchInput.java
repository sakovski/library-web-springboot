package com.srutkowski.libraryweb.search;

public class SearchInput {

    private String userInput;

    public SearchInput(String userInput) {
        this.userInput = userInput;
    }

    public SearchInput() {

    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }
}
