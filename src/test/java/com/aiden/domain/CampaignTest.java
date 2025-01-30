package com.aiden.domain;

import autoparams.AutoSource;
import autoparams.Repeat;
import autoparams.customization.Customization;
import autoparams.generator.Factory;
import com.aiden.domain.enums.CampaignType;
import com.aiden.domain.generator.BudgetGenerator;
import com.aiden.domain.generator.CampaignGenerator;
import com.aiden.domain.generator.InvalidBudgetGenerator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@DisplayName("캠페인")
class CampaignTest {

    @Repeat(10)
    @AutoSource
    @ParameterizedTest
    void 기본_검증(Campaign campaign) {
        log.info("campaign : {}", campaign);
        assertThat(campaign).isNotNull();
    }

    @Repeat(10)
    @AutoSource
    @ParameterizedTest
    @Customization(BudgetGenerator.class)
    void 특정_캠페인_타입_고정(Factory<Campaign> campaignFactory) {
        // given
        CampaignType freezeCampaignType = CampaignType.DISPLAY;
        campaignFactory.applyCustomizer(
                CampaignGenerator.builder().freezeCampaignType(freezeCampaignType).build());

        // when
        Campaign campaign = campaignFactory.get();
        log.info("campaign : {}", campaign);

        // then
        assertThat(campaign.getCampaignType()).isEqualTo(freezeCampaignType);
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
