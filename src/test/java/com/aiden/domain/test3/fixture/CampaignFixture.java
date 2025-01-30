package com.aiden.domain.test3.fixture;

import com.aiden.domain.Campaign;
import com.aiden.domain.CampaignTypeGoal;
import com.aiden.domain.enums.CampaignGoal;
import com.aiden.domain.enums.CampaignType;
import com.aiden.domain.enums.UserStatus;
import com.aiden.domain.vo.Budget;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class CampaignFixture {

    public static Campaign createCampaign(long dailyBudget) {
        return Campaign.builder()
                .id(1L)
                .budget(Budget.builder().dailyBudget(dailyBudget).dailyBudgetOverDttm(LocalDateTime.now()).build())
                .campaignTypeGoal(CampaignTypeGoal.builder().campaignType(CampaignType.DISPLAY).campaignGoal(CampaignGoal.IMPRESSION).build())
                .userStatus(UserStatus.ON)
                .updatedDate(LocalDateTime.now())
                .createdDate(LocalDateTime.now())
                .build();
    }
}
