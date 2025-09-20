package org.example.models;

public class GroupExpenseShare {

    public int userId;
    public double percentShare;
    public double exactShare;

    public GroupExpenseShare(int userId, double percentShare, double exactShare) {
        this.userId = userId;
        this.percentShare = percentShare;
        this.exactShare = exactShare;
    }

}
