package com.devstaff.assessment.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class FarmData {
    private String farmName;
    private int expectedYield;
    private int actualYield;
}
