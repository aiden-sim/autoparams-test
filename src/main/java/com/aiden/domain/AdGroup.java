package com.aiden.domain;

import com.aiden.domain.enums.UserStatus;
import com.aiden.domain.vo.Budget;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@AllArgsConstructor
public class AdGroup {
    private Long id;
    private Campaign campaign;
    private Budget budget;
    private UserStatus userStatus;
    private LocalDateTime updatedDate;
    private LocalDateTime createdDate;
}
