package com.company.models;

public class User {
    public int id;
    public String registration;
    public String name;

    public User(String registration, String name) {
        this.registration = registration;
        this.name = name;
    }

    public User(int id, String registration, String name) {
        this.id = id;
        this.registration = registration;
        this.name = name;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public void setName(String name) {
        this.name = name;
    }
}
