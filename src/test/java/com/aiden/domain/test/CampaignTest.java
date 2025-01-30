package com.aiden.domain.test;

import com.aiden.domain.Campaign;
import com.aiden.domain.CampaignTypeGoal;
import com.aiden.domain.enums.CampaignGoal;
import com.aiden.domain.enums.CampaignType;
import com.aiden.domain.enums.UserStatus;
import com.aiden.domain.vo.Budget;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 1) 중복 선언
 */
@Slf4j
@DisplayName("캠페인")
class CampaignTest {

    @Test
    void 유효한_일예산() {
        Campaign campaign = Campaign.builder()
                .id(1L)
                .budget(Budget.builder().dailyBudget(10_000L).dailyBudgetOverDttm(LocalDateTime.now()).build())
                .campaignTypeGoal(CampaignTypeGoal.builder().campaignType(CampaignType.DISPLAY).campaignGoal(CampaignGoal.IMPRESSION).build())
                .userStatus(UserStatus.ON)
                .updatedDate(LocalDateTime.now())
                .createdDate(LocalDateTime.now())
                .build();
        assertTrue(campaign.getBudget().validBudget());
    }

    @Test
    void 잘못된_일예산() {
        Campaign campaign = Campaign.builder()
                .id(1L)
                .budget(Budget.builder().dailyBudget(10L).dailyBudgetOverDttm(LocalDateTime.now()).build())
                .campaignTypeGoal(CampaignTypeGoal.builder().campaignType(CampaignType.DISPLAY).campaignGoal(CampaignGoal.IMPRESSION).build())
                .userStatus(UserStatus.ON)
                .updatedDate(LocalDateTime.now())
                .createdDate(LocalDateTime.now())
                .build();
        assertFalse(campaign.getBudget().validBudget());
    }
}
