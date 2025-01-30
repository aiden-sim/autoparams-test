package com.aiden.domain.generator;

import autoparams.ObjectQuery;
import autoparams.ResolutionContext;
import autoparams.generator.ObjectGeneratorBase;
import com.aiden.domain.CampaignTypeGoal;
import com.aiden.domain.enums.CampaignGoal;
import com.aiden.domain.enums.CampaignType;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CampaignTypeGoalGenerator extends ObjectGeneratorBase<CampaignTypeGoal> {
    @Override
    protected CampaignTypeGoal generateObject(ObjectQuery query, ResolutionContext context) {
        CampaignType campaignType = context.resolve(CampaignType.class);

        ThreadLocalRandom random = ThreadLocalRandom.current();
        List<CampaignGoal> goals = campaignType.getGoals();
        CampaignGoal campaignGoal = goals.get(random.nextInt(goals.size()));

        return CampaignTypeGoal.builder()
                .campaignType(campaignType)
                .campaignGoal(campaignGoal)
                .build();
    }
}
