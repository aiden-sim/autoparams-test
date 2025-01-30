package com.aiden.domain.test3.fixture;

import com.aiden.domain.AdGroup;
import com.aiden.domain.Campaign;
import com.aiden.domain.enums.UserStatus;
import com.aiden.domain.vo.Budget;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class AdGroupFixture {

    public static AdGroup createAdGroup(Campaign campaign, long dailyBudget) {
        return AdGroup.builder()
                .id(1L)
                .campaign(campaign)
                .budget(Budget.builder().dailyBudget(dailyBudget).dailyBudgetOverDttm(LocalDateTime.now()).build())
                .userStatus(UserStatus.ON)
                .updatedDate(LocalDateTime.now())
                .createdDate(LocalDateTime.now())
                .build();
    }
}
