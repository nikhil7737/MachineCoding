package org.example.services.strategies;

import org.example.models.GroupExpense;
import org.example.models.GroupExpenseShare;

public class ExactSplitStrategy implements ISplitStrategy{


    @Override
    public boolean validateGroupExpense(GroupExpense expense) {
        Double totalShare = 0d;
        for (GroupExpenseShare share : expense.expenseShares) {
            totalShare += share.exactShare;
        }
        if (totalShare.compareTo(expense.totalAmount) != 0) {
            System.out.println("sum of amounts is not matching");
            return false;
        }
        return true;
    }

    @Override
    public void calculateExactShare(GroupExpense expense) {

    }
}
