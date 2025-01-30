package com.aiden.domain;

import autoparams.AutoSource;
import autoparams.Repeat;
import autoparams.customization.Customization;
import com.aiden.domain.generator.BudgetGenerator;
import com.aiden.domain.generator.InvalidBudgetGenerator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@DisplayName("광고그룹")
class AdGroupTest {

    @Repeat(10)
    @AutoSource
    @ParameterizedTest
    void 기본_검증(AdGroup adGroup) {
        log.info("adGroup : {}", adGroup);
        assertThat(adGroup).isNotNull();
    }

    @AutoSource
    @ParameterizedTest
    @Customization(BudgetGenerator.class)
    void 유효한_일예산(AdGroup adGroup) {
        log.info("adGroup : {}", adGroup);
        assertTrue(adGroup.getBudget().validBudget());
    }

    @AutoSource
    @ParameterizedTest
    @Customization(InvalidBudgetGenerator.class)
    void 잘못된_일예산(AdGroup adGroup) {
        log.info("adGroup : {}", adGroup);
        assertFalse(adGroup.getBudget().validBudget());
    }
}
