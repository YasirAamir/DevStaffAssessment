package com.devstaff.assessment.entity;

import com.devstaff.assessment.dto.CreateHarvestRecord;
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
@Table(name = "HARVEST")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Harvest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "harvest_id")
    private long harvestId;

    @ManyToOne
    @JoinColumn(name = "farm_id", nullable = false)
    private Farm farm;

    @ManyToOne
    @JoinColumn(name = "season_id", nullable = false)
    private Season season;

    @ManyToOne
    @JoinColumn(name = "crop_id", nullable = false)
    private Crop crop;

    @Column(name = "actual_yield")
    private int actualYield;

    public static Harvest createHarvestEntity(CreateHarvestRecord createHarvestRecord){
        return Harvest.builder()
                .farm(Farm.builder().farmId(createHarvestRecord.getFarmId()).build())
                .season(Season.builder().seasonId(createHarvestRecord.getSeasonId()).build())
                .crop(Crop.builder().cropId(createHarvestRecord.getCropId()).build())
                .actualYield(createHarvestRecord.getActualYield())
                .build();
    }
}
