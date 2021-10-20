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
    public User getUserById(int id){
        for (User user : this.users) {
            if (user.id == id) {
                return user;
            }
        }
        return null;
    }
}
