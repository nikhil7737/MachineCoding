package org.example.services;

import org.example.Main;
import org.example.models.*;
import org.example.services.strategies.ISplitStrategy;

import java.util.ArrayList;
import java.util.List;

public class ExpenseSharingService {

    private final SplitStrategyFactory factory;
    private final UserService userService;

    public ExpenseSharingService(SplitStrategyFactory factory, UserService userService) {
        this.factory = factory;
        this.userService = userService;
    }


    public void addExpense(int paidBy, List<GroupExpenseShare> expenseShares, ExpenseType expenseType, String expenseName, double totalAmount) {
        GroupExpense groupExpense = new GroupExpense();
        groupExpense.expenseName = expenseName;
        groupExpense.expenseShares = expenseShares;
        groupExpense.totalAmount = totalAmount;
        groupExpense.expenseType = expenseType;
        groupExpense.paidBy = paidBy;

        ISplitStrategy splitStrategy = factory.getStrategy(expenseType);
        if (!splitStrategy.validateGroupExpense(groupExpense)) {
            return;
        }

        splitStrategy.calculateExactShare(groupExpense);
        for (GroupExpenseShare share : groupExpense.expenseShares) {
            userService.addExpense(share.userId, expenseName, share.exactShare);
            if (share.userId == paidBy) {
                userService.addBalance(share.userId, totalAmount - share.exactShare);
            }
            else {
                userService.addBalance(share.userId, -share.exactShare);
            }
        }

    }

    public void generateSettlementReport() {
        System.out.println("Generating settlement report");
        List<User> positiveBalances = new ArrayList<>();
        List<User> negativeBalances = new ArrayList<>();
        List<User> allUsers = userService.getAllUsers();

        for (User user : allUsers) {
            if (user.balance > 0) {
                positiveBalances.add(user);
            }
            else if (user.balance < 0) {
                negativeBalances.add(user);
            }
        }

        List<ExpenseSettlement> settlements = new ArrayList<>();
        int i = 0, j = 0;
        while (i < positiveBalances.size()) {
            double b1 = Math.abs(positiveBalances.get(i).balance);
            double b2 = Math.abs(negativeBalances.get(j).balance);
            double min = Math.min(b1, b2);

            ExpenseSettlement settlement = new ExpenseSettlement();
            settlement.toBePaidBy = negativeBalances.get(j).userId;
            settlement.toBePaidTo = positiveBalances.get(i).userId;
            settlement.amount = min;
            settlements.add(settlement);

            System.out.println(settlement.toBePaidBy + " needs to pay " + settlement.amount + " to " + settlement.toBePaidTo);

            positiveBalances.get(i).balance -= min;
            negativeBalances.get(i).balance += min;

            if (b1 < b2) {
                i++;
            }
            else if (b1 > b2) {
                j++;
            }
            else {
                i++;
                j++;
            }
        }

        System.out.println("report generated");
        System.out.println();

    }

}
