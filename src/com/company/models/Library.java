package com.company.models;

import com.company.db.Database;

import java.sql.SQLException;
import java.util.ArrayList;

public class Library {
    public ArrayList<User> users;
    public ArrayList<Publication> publications;

    public Library() {
        this.users = new ArrayList<>();
        this.publications = new ArrayList<>();
    }

    public ArrayList<User> getAllUsers() throws SQLException {
        this.users = Database.getAllUsers();
        return users;
    }

    public ArrayList<Publication> getAllPublications() throws SQLException {
        this.publications = Database.getAllPublication();
        return publications;
    }
    public User getUserById(int id){
        for (User user : this.users) {
            if (user.id == id) {
                return user;
            }
        }
        return null;
    }

    public Publication getPublicationById(int id){
        for (Publication publication : this.publications) {
            if (publication.id == id) {
                return publication;
            }
        }
        return null;
    }
}
