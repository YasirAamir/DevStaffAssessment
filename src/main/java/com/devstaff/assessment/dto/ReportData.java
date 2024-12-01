package com.devstaff.assessment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ReportData {
    private String season;
    private List<FarmData> farmData;
    private List<CropData> cropData;
}
