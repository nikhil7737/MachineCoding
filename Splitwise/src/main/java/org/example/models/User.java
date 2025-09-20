package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class User {

    public int userId;
    public double balance = 0;
    public List<UserExpense> expenses = new ArrayList<>();
    public String name;
}
