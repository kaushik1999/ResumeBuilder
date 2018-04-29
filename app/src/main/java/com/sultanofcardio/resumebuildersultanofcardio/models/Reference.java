package com.sultanofcardio.resumebuildersultanofcardio.models;

/**
 * @author sultanofcardio
 */

public class Reference {

    private String name;

    private String title;

    private String number;

    private String email;

    public Reference(String name, String title, String number, String email) {
        this.name = name;
        this.title = title;
        this.number = number;
        this.email = email;
    }

    public Reference() {
        this.name = "";
        this.title = "";
        this.number = "";
        this.email = "";
    }

    public String getName() {
        return name;
    }

    public Reference setName(String name) {
        this.name = name;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Reference setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public Reference setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Reference setEmail(String email) {
        this.email = email;
        return this;
    }
}
