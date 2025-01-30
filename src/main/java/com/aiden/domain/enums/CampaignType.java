package com.aiden.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum CampaignType {
    DISPLAY(List.of(CampaignGoal.IMPRESSION, CampaignGoal.CLICK, CampaignGoal.CONVERSION)),
    VIDEO(List.of(CampaignGoal.VIEW)),
    MESSAGE(List.of(CampaignGoal.REACH));

    private final List<CampaignGoal> goals;
}
