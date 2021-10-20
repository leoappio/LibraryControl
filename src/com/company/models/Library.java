package com.company.models;

import java.util.ArrayList;

public class Library {
    public ArrayList<User> users;
    public ArrayList<Publication> publications;

    public Library() {
        this.users = new ArrayList<>();
        this.publications = new ArrayList<>();
    }
}
