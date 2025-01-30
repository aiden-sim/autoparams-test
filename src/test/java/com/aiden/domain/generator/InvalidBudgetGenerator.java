package com.aiden.domain.generator;

import autoparams.ObjectQuery;
import autoparams.ResolutionContext;
import autoparams.generator.ObjectGeneratorBase;
import com.aiden.domain.vo.Budget;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

public class InvalidBudgetGenerator extends ObjectGeneratorBase<Budget> {
    private static final int MIN_BUDGET = Integer.MIN_VALUE;
    private static final int MAX_BUDGET = Integer.MAX_VALUE;

    @Override
    protected Budget generateObject(ObjectQuery query, ResolutionContext context) {
        Long dailyBudget = generateInvalidBudget();
        LocalDateTime dailyBudgetOverDttm = context.resolve(LocalDateTime.class);

        return new Budget(dailyBudget, dailyBudgetOverDttm);
    }

    private Long generateInvalidBudget() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return random.nextBoolean()
                ? random.nextLong(MIN_BUDGET, Budget.DAILY_BUDGET_MIN)
                : random.nextLong(Budget.DAILY_BUDGET_MAX, MAX_BUDGET);
    }
}
