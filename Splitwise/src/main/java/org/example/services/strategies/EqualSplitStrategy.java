package org.example.services.strategies;

import org.example.models.GroupExpense;
import org.example.models.GroupExpenseShare;

public class EqualSplitStrategy implements ISplitStrategy{
    @Override
    public boolean validateGroupExpense(GroupExpense expense) {
        return true;
    }

    @Override
    public void calculateExactShare(GroupExpense expense) {
        //validate that % values should not be set
        double sharePerUser = expense.totalAmount / expense.expenseShares.size();
        for (GroupExpenseShare share : expense.expenseShares) {
            share.exactShare = sharePerUser;
        }
    }
}
