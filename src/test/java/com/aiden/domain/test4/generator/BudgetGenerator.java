package com.aiden.domain.test4.generator;

import autoparams.ObjectQuery;
import autoparams.ResolutionContext;
import autoparams.generator.ObjectGeneratorBase;
import com.aiden.domain.vo.Budget;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

public class BudgetGenerator extends ObjectGeneratorBase<Budget> {
    @Override
    protected Budget generateObject(ObjectQuery query, ResolutionContext context) {
        LocalDateTime dailyBudgetOverDttm = context.resolve(LocalDateTime.class);
        Long dailyBudget = generateBudget();

        return Budget.builder()
                .dailyBudget(dailyBudget)
                .dailyBudgetOverDttm(dailyBudgetOverDttm)
                .build();
    }

    private Long generateBudget() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return random.nextLong(Budget.DAILY_BUDGET_MIN, Budget.DAILY_BUDGET_MAX);
    }
}
