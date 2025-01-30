package com.aiden.domain;

import autoparams.AutoSource;
import autoparams.Repeat;
import autoparams.customization.Customization;
import autoparams.generator.Factory;
import com.aiden.domain.generator.*;
import com.aiden.domain.vo.Budget;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@DisplayName("캠페인")
class CampaignTest {

    @Repeat(10)
    @AutoSource
    @ParameterizedTest
    @Customization(CampaignCustomizer.class)
    void 기본_검증(Campaign campaign) {
        log.info("campaign : {}", campaign);
        assertThat(campaign).isNotNull();
    }

    @AutoSource
    @ParameterizedTest
    @Customization({BudgetGenerator.class, CampaignTypeGoalGenerator.class})
    void 특정_캠페인_타입과_예산_고정(Factory<Campaign> campaignFactory,
                          Budget freeBudget,
                          CampaignTypeGoal freezeCampaignTypeGoal) {
        // given
        campaignFactory.applyCustomizer(
                CampaignGenerator.builder()
                        .freezeCampaignTypeGoal(freezeCampaignTypeGoal)
                        .freezeBudget(freeBudget)
                        .build()
        );

        // when
        Campaign campaign = campaignFactory.get();
        List<Campaign> campaigns = campaignFactory.stream().limit(3).toList();
        log.info("campaign : {}", campaign);
        log.info("campaigns : {}", campaigns);

        // then
        assertThat(campaign.getCampaignTypeGoal()).isEqualTo(freezeCampaignTypeGoal);
        assertTrue(campaign.getBudget().validBudget());
    }

    @AutoSource
    @ParameterizedTest
    @Customization(BudgetGenerator.class)
    void 유효한_일예산(Campaign campaign) {
        log.info("campaign : {}", campaign);
        assertTrue(campaign.getBudget().validBudget());
    }

    @AutoSource
    @ParameterizedTest
    @Customization(InvalidBudgetGenerator.class)
    void 잘못된_일예산(Campaign campaign) {
        log.info("campaign : {}", campaign);
        assertFalse(campaign.getBudget().validBudget());
    }
}
