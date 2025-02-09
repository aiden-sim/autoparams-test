package com.aiden.domain.test4.generator;

import autoparams.ObjectQuery;
import autoparams.ResolutionContext;
import autoparams.generator.ObjectGeneratorBase;
import com.aiden.domain.Campaign;
import com.aiden.domain.CampaignTypeGoal;
import com.aiden.domain.enums.UserStatus;
import com.aiden.domain.vo.Budget;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignGenerator extends ObjectGeneratorBase<Campaign> {
    private Long freezeId;
    private Budget freezeBudget;
    private CampaignTypeGoal freezeCampaignTypeGoal;
    private UserStatus freezeUserStatus;
    private LocalDateTime freezeUpdatedDate;
    private LocalDateTime freezeCreatedDate;

    @Override
    protected Campaign generateObject(ObjectQuery query, ResolutionContext context) {
        Long id = context.resolve(Long.class);
        Budget budget = context.resolve(Budget.class);
        CampaignTypeGoal campaignTypeGoal = context.resolve(CampaignTypeGoal.class);
        UserStatus userStatus = context.resolve(UserStatus.class);
        LocalDateTime updatedDate = context.resolve(LocalDateTime.class);
        LocalDateTime createdDate = context.resolve(LocalDateTime.class);

        return Campaign.builder()
                .id(Objects.nonNull(freezeId) ? freezeId : id)
                .budget(Objects.nonNull(freezeBudget) ? freezeBudget : budget)
                .campaignTypeGoal(Objects.nonNull(freezeCampaignTypeGoal) ? freezeCampaignTypeGoal : campaignTypeGoal)
                .userStatus(Objects.nonNull(freezeUserStatus) ? freezeUserStatus : userStatus)
                .updatedDate(Objects.nonNull(freezeUpdatedDate) ? freezeUpdatedDate : updatedDate)
                .createdDate(Objects.nonNull(freezeCreatedDate) ? freezeCreatedDate : createdDate)
                .build();
    }
}
