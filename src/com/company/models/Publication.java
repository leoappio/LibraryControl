package com.company.models;

import java.util.ArrayList;

public class Publication {
    public int id;
    public String title;
    public String author;
    public int quantity;
    public ArrayList<Loan> loanHistory;

    public Publication(int id, String title, String author, int quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.quantity = quantity;
        this.loanHistory = new ArrayList<>();
    }

    public Publication(String title, String author, int quantity) {
        this.title = title;
        this.author = author;
        this.quantity = quantity;
        this.loanHistory = new ArrayList<>();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void increaseQuantity(){
        this.quantity++;
    }

    public void decreaseQuantity(){
        this.quantity--;
    }
}
