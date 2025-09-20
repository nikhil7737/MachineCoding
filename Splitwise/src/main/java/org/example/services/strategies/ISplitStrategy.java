package org.example.services.strategies;

import org.example.models.GroupExpense;

public interface ISplitStrategy {

    boolean validateGroupExpense(GroupExpense expense);
    void calculateExactShare(GroupExpense expense);

}
