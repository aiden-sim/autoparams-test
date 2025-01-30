package com.aiden.domain;

import com.aiden.domain.enums.CampaignGoal;
import com.aiden.domain.enums.CampaignType;
import lombok.*;

@Getter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class CampaignTypeGoal {
    private CampaignType campaignType;
    private CampaignGoal campaignGoal;
}
