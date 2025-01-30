package com.aiden.domain.test4;

import autoparams.AutoSource;
import autoparams.Repeat;
import autoparams.customization.Customization;
import com.aiden.domain.AdGroup;
import com.aiden.domain.test4.generator.BudgetGenerator;
import com.aiden.domain.test4.generator.InvalidBudgetGenerator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 4) autoParams 를 이용한 자동 test fixture 생성
 */
@Slf4j
@DisplayName("광고그룹")
class AdGroupTest {

    @Repeat(10)
    @AutoSource
    @ParameterizedTest
    void 기본_검증(AdGroup adGroup) {
        log.info("adGroup : {}", adGroup);
        log.info("campaign : {}", adGroup.getCampaign());
        assertThat(adGroup).isNotNull();
    }

    @AutoSource
    @ParameterizedTest
    @Customization(BudgetGenerator.class)
    void 유효한_일예산(AdGroup adGroup) {
        log.info("adGroup : {}", adGroup);
        log.info("campaign : {}", adGroup.getCampaign());
        assertTrue(adGroup.getBudget().validBudget());
    }

    @AutoSource
    @ParameterizedTest
    @Customization(InvalidBudgetGenerator.class)
    void 잘못된_일예산(AdGroup adGroup) {
        log.info("adGroup : {}", adGroup);
        log.info("campaign : {}", adGroup.getCampaign());
        assertFalse(adGroup.getBudget().validBudget());
    }
}
