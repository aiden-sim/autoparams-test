package com.aiden.domain.test3;

import com.aiden.domain.AdGroup;
import com.aiden.domain.Campaign;
import com.aiden.domain.test3.fixture.AdGroupFixture;
import com.aiden.domain.test3.fixture.CampaignFixture;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 3) 클래스 형태의 test fixture
 */
@Slf4j
@DisplayName("광고그룹")
class AdGroupTest {

    @Test
    void 유효한_일예산() {
        Campaign campaign = CampaignFixture.createCampaign(10_000L);
        AdGroup adGroup = AdGroupFixture.createAdGroup(campaign, 10_000L);
        assertTrue(adGroup.getBudget().validBudget());
    }

    @Test
    void 잘못된_일예산() {
        Campaign campaign = CampaignFixture.createCampaign(10_000L);
        AdGroup adGroup = AdGroupFixture.createAdGroup(campaign, 10L);
        assertFalse(adGroup.getBudget().validBudget());
    }
}
