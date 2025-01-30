package com.aiden.domain.test4.generator;

import autoparams.ObjectQuery;
import autoparams.ResolutionContext;
import autoparams.generator.ObjectGeneratorBase;
import com.aiden.domain.vo.Budget;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

public class InvalidBudgetGenerator extends ObjectGeneratorBase<Budget> {
    private static final Long MIN_BUDGET = Long.MIN_VALUE;
    private static final Long MAX_BUDGET = Long.MAX_VALUE;

    @Override
    protected Budget generateObject(ObjectQuery query, ResolutionContext context) {
        Long dailyBudget = generateInvalidBudget();
        LocalDateTime dailyBudgetOverDttm = context.resolve(LocalDateTime.class);
        return Budget.builder()
                .dailyBudget(dailyBudget)
                .dailyBudgetOverDttm(dailyBudgetOverDttm)
                .build();
    }

    private Long generateInvalidBudget() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return random.nextBoolean()
                ? random.nextLong(MIN_BUDGET, Budget.DAILY_BUDGET_MIN)
                : random.nextLong(Budget.DAILY_BUDGET_MAX, MAX_BUDGET);
    }
}
