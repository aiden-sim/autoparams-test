package com.aiden.domain.test4;

import autoparams.AutoSource;
import autoparams.MethodAutoSource;
import autoparams.Repeat;
import autoparams.customization.Customization;
import autoparams.generator.Factory;
import com.aiden.domain.Campaign;
import com.aiden.domain.CampaignTypeGoal;
import com.aiden.domain.enums.CampaignGoal;
import com.aiden.domain.enums.CampaignType;
import com.aiden.domain.test4.generator.*;
import com.aiden.domain.vo.Budget;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 4) autoParams 를 이용한 자동 test fixture 생성
 */
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

    @ParameterizedTest
    @MethodAutoSource("generateMethod")
    void 특정_캠페인_타입과_예산_고정(
            Budget freeBudget,
            CampaignTypeGoal freezeCampaignTypeGoal,
            Factory<Campaign> campaignFactory) {

        // given
        campaignFactory.applyCustomizer(
                CampaignGenerator.builder()
                        .freezeBudget(freeBudget)
                        .freezeCampaignTypeGoal(freezeCampaignTypeGoal)
                        .build()
        );

        // when
        Campaign campaign = campaignFactory.get();
        log.info("campaign : {}", campaign);

        List<Campaign> campaigns = campaignFactory.stream().limit(10).toList();
        log.info("campaigns : {}", campaigns);

        // then
        assertThat(campaign.getCampaignTypeGoal()).isEqualTo(freezeCampaignTypeGoal);
        assertTrue(campaign.getBudget().validBudget());

        assertThat(campaigns).hasSize(10);
    }

    private static Stream<Arguments> generateMethod() {
        return Stream.of(
                Arguments.of(
                        Budget.builder().dailyBudget(10_000L).dailyBudgetOverDttm(LocalDateTime.now()).build(),
                        CampaignTypeGoal.builder().campaignType(CampaignType.DISPLAY).campaignGoal(CampaignGoal.IMPRESSION).build()),

                Arguments.of(
                        Budget.builder().dailyBudget(20_000L).dailyBudgetOverDttm(LocalDateTime.now()).build(),
                        CampaignTypeGoal.builder().campaignType(CampaignType.MESSAGE).campaignGoal(CampaignGoal.REACH).build()),

                Arguments.of(
                        Budget.builder().dailyBudget(30_000L).dailyBudgetOverDttm(LocalDateTime.now()).build(),
                        CampaignTypeGoal.builder().campaignType(CampaignType.VIDEO).campaignGoal(CampaignGoal.VIEW).build()));
    }


}
