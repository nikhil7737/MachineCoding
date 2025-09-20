package org.example.services;

import org.example.models.User;
import org.example.models.UserExpense;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {

    Map<Integer, User> userMap = new HashMap<>();


    public void addUser(String name) {
        int userId = userMap.size() + 1;
        User user = new User();
        user.name = name;
        user.userId = userId;
        userMap.put(userId, user);
    }

    public void addExpense(int userId, String expenseName, double amount) {
        validateUserId(userId);

        User user = userMap.get(userId);
        user.expenses.add(new UserExpense(amount, expenseName));
    }

    public double getUserBalance(int userId) {
        validateUserId(userId);
        return userMap.get(userId).balance;
    }

    public void printUserExpenses(int userId) {
        validateUserId(userId);
        System.out.println("Expenses of user " + userId);
        for (UserExpense expense : userMap.get(userId).expenses) {
            System.out.println("paid " + expense.amount + " for " + expense.expenseName);
        }
        System.out.println();
    }

    private void validateUserId(int userId) {
        if (!userMap.containsKey(userId)) {
            System.out.println("invalid user id");
        }
    }

    public List<User> getAllUsers() {
        return userMap.values().stream().toList();
    }

    public void addBalance(int userId, double value) {
        validateUserId(userId);
        userMap.get(userId).balance += value;
    }
}
