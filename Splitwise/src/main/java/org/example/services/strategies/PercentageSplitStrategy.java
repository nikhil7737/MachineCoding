package org.example.services.strategies;

import org.example.models.GroupExpense;
import org.example.models.GroupExpenseShare;

public class PercentageSplitStrategy implements ISplitStrategy{
    @Override
    public boolean validateGroupExpense(GroupExpense expense) {
        Double percentSum = 0D;
        for (GroupExpenseShare share : expense.expenseShares) {
            percentSum += share.percentShare;
        }

        if (percentSum.compareTo(100D) != 0) {
            System.out.println("sum of percentages is not matching");
            return false;
        }
        return true;
    }

    @Override
    public void calculateExactShare(GroupExpense expense) {
        for (GroupExpenseShare share : expense.expenseShares) {
            share.exactShare = share.percentShare * expense.totalAmount / 100;
        }
    }
}
