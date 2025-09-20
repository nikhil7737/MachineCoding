package org.example.services;

import org.example.models.ExpenseType;
import org.example.services.strategies.EqualSplitStrategy;
import org.example.services.strategies.ExactSplitStrategy;
import org.example.services.strategies.ISplitStrategy;
import org.example.services.strategies.PercentageSplitStrategy;

import java.util.Map;

public class SplitStrategyFactory {

    private final Map<ExpenseType, ISplitStrategy> strategyMap = Map.of(
            ExpenseType.EQUAL, new EqualSplitStrategy(),
            ExpenseType.EXACT, new ExactSplitStrategy(),
            ExpenseType.PERCENTAGE, new PercentageSplitStrategy()
    );

    public ISplitStrategy getStrategy(ExpenseType expenseType) {
        return strategyMap.get(expenseType);
    }

}
