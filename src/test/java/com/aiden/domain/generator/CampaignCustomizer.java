package com.aiden.domain.generator;

import autoparams.customization.CompositeCustomizer;

public class CampaignCustomizer extends CompositeCustomizer {
    public CampaignCustomizer() {
        super(
                new CampaignGenerator(),
                new BudgetGenerator(),
                new CampaignTypeGoalGenerator()
        );
    }
}
