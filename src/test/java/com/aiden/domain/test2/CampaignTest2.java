package com.aiden.domain.test2;

import com.aiden.domain.Campaign;
import com.aiden.domain.CampaignTypeGoal;
import com.aiden.domain.enums.CampaignGoal;
import com.aiden.domain.enums.CampaignType;
import com.aiden.domain.enums.UserStatus;
import com.aiden.domain.vo.Budget;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 2) BeforeEach(setup) 을 통한 테스트
 */
@Slf4j
@DisplayName("캠페인")
class CampaignTest2 {

    private Campaign validCampaign;

    @BeforeEach
    void setup() {
        validCampaign = Campaign.builder()
                .id(1L)
                .budget(Budget.builder().dailyBudget(10_000L).dailyBudgetOverDttm(LocalDateTime.now()).build())
                .campaignTypeGoal(CampaignTypeGoal.builder().campaignType(CampaignType.DISPLAY).campaignGoal(CampaignGoal.IMPRESSION).build())
                .userStatus(UserStatus.ON)
                .updatedDate(LocalDateTime.now())
                .createdDate(LocalDateTime.now())
                .build();
    }

    @Test
    void 유효한_일예산() {
        assertTrue(validCampaign.getBudget().validBudget());
    }
}
