package com.devstaff.assessment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateHarvestRecord {
    private long farmId;
    private long seasonId;
    private long cropId;
    private int actualYield;
}
