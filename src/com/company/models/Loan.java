package com.company.models;

public class Loan {
    public int loanId;
    public int userId;
    public int publicationId;
    public int lateDays;
    public String isReturned;

    public Loan(int userId, int publicationId, int lateDays, String isReturned){
        this.userId = userId;
        this.publicationId = publicationId;
        this.lateDays = lateDays;
        this.isReturned = isReturned;
    }
    public Loan(int loanId, int userId, int publicationId, int lateDays, String isReturned){
        this.loanId = loanId;
        this.userId = userId;
        this.publicationId = publicationId;
        this.lateDays = lateDays;
        this.isReturned = isReturned;
    }

    public void setLateDays(int lateDays) {
        this.lateDays = lateDays;
    }

    public void setIsReturned(String isReturned) {
        this.isReturned = isReturned;
    }
}
