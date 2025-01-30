package com.aiden.domain;

import com.aiden.domain.enums.CampaignType;
import com.aiden.domain.enums.UserStatus;
import com.aiden.domain.vo.Budget;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@AllArgsConstructor
public class Campaign {
    private Long id;
    private Budget budget;
    private CampaignTypeGoal campaignTypeGoal;
    private UserStatus userStatus;
    private LocalDateTime updatedDate;
    private LocalDateTime createdDate;
}
