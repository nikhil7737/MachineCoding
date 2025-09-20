package org.example.models;

import java.util.List;

public class GroupExpense {

    public String expenseName;
    public double totalAmount;
    public int paidBy;
    public ExpenseType expenseType;
    public List<GroupExpenseShare> expenseShares;

}
