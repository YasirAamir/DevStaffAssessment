package com.devstaff.assessment.entity;

import com.devstaff.assessment.dto.CreatePlantRecord;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Table(name = "PLANT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "plant_id")
    private long plantId;

    @ManyToOne
    @JoinColumn(name = "farm_id", nullable = false)
    private Farm farm;

    @ManyToOne
    @JoinColumn(name = "season_id", nullable = false)
    private Season season;

    @ManyToOne
    @JoinColumn(name = "crop_id", nullable = false)
    private Crop crop;

    @Column(name = "planted_area")
    private int plantedArea;

    @Column(name = "expected_yield")
    private int expectedYield;

    public static Plant createPlantEntity(CreatePlantRecord createPlantRecord){
       return Plant.builder()
                .farm(Farm.builder().farmId(createPlantRecord.getFarmId()).build())
                .season(Season.builder().seasonId(createPlantRecord.getSeasonId()).build())
                .crop(Crop.builder().cropId(createPlantRecord.getCropId()).build())
                .plantedArea(createPlantRecord.getPlantedArea())
                .expectedYield(createPlantRecord.getExpectedYield())
                .build();
    }
}
