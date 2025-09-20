package org.example;



//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import org.example.models.ExpenseType;
import org.example.models.GroupExpenseShare;
import org.example.services.ExpenseSharingService;
import org.example.services.SplitStrategyFactory;
import org.example.services.UserService;

import java.util.List;

public class Main {
    public static void main(String[] args) {

//        test1();
        test2();
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.



    }

    private static void test1() {
        UserService userService = new UserService();
        SplitStrategyFactory factory = new SplitStrategyFactory();
        ExpenseSharingService expenseSharingService = new ExpenseSharingService(factory, userService);

        userService.addUser("Nikhil");
        userService.addUser("Vishal");
        userService.addUser("Ravindra");

        expenseSharingService.addExpense(
                1,
                List.of(
                        new GroupExpenseShare(1, 0, 0),
                        new GroupExpenseShare(2, 0, 0),
                        new GroupExpenseShare(3, 0, 0)
                ),
                ExpenseType.EQUAL,
                "trip1",
                300
        );

        expenseSharingService.addExpense(
                2,
                List.of(
                        new GroupExpenseShare(2, 40, 0),
                        new GroupExpenseShare(3, 60, 0)
                ),
                ExpenseType.PERCENTAGE,
                "trip1",
                100
        );

        expenseSharingService.generateSettlementReport();
    }

    static void test2() {
        UserService userService = new UserService();
        SplitStrategyFactory factory = new SplitStrategyFactory();
        ExpenseSharingService expenseSharingService = new ExpenseSharingService(factory, userService);

        userService.addUser("Nikhil");
        userService.addUser("Vishal");
        userService.addUser("Ravindra");

        expenseSharingService.addExpense(
                1,
                List.of(
                        new GroupExpenseShare(1, 0, 0),
                        new GroupExpenseShare(2, 0, 0),
                        new GroupExpenseShare(3, 0, 0)
                ),
                ExpenseType.EXACT,
                "trip1",
                300
        );

        expenseSharingService.addExpense(
                2,
                List.of(
                        new GroupExpenseShare(2, 40, 0),
                        new GroupExpenseShare(3, 60, 0)
                ),
                ExpenseType.PERCENTAGE,
                "trip1",
                100
        );

        expenseSharingService.generateSettlementReport();
    }
}