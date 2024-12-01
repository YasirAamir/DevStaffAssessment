package com.devstaff.assessment.dto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePlantRecord {
    private long farmId;
    private long seasonId;
    private long cropId;
    private int plantedArea;
    private int expectedYield;
}
