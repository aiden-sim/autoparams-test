package com.aiden.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
public class Budget {
    public static final long DAILY_BUDGET_MIN = 10_000;
    public static final long DAILY_BUDGET_MAX = 10_000_000;

    private Long dailyBudget;
    private LocalDateTime dailyBudgetOverDttm;

    public boolean validBudget() {
        return dailyBudget >= DAILY_BUDGET_MIN && dailyBudget <= DAILY_BUDGET_MAX;
    }
}
