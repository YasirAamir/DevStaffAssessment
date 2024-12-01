package com.devstaff.assessment.service;

import com.devstaff.assessment.dto.CropData;
import com.devstaff.assessment.dto.FarmData;
import com.devstaff.assessment.dto.ReportData;
import com.devstaff.assessment.entity.Harvest;
import com.devstaff.assessment.entity.Plant;
import com.devstaff.assessment.repository.HarvestRepository;
import com.devstaff.assessment.repository.PlantRepository;
import com.devstaff.assessment.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecordService {

    private final HarvestRepository harvestRepository;
    private final PlantRepository plantRepository;
    private final SeasonRepository seasonRepository;

    @Autowired
    public RecordService(HarvestRepository harvestRepository, PlantRepository plantRepository, SeasonRepository seasonRepository) {
        this.harvestRepository = harvestRepository;
        this.plantRepository = plantRepository;
        this.seasonRepository = seasonRepository;
    }

    public ReportData getReportFromFarm(long seasonId){

        List<Plant> plantedList = plantRepository.findBySeason_SeasonId(seasonId);
        List<Harvest> harvestedList = harvestRepository.findBySeason_SeasonId(seasonId);

        AbstractMap.SimpleEntry<Map<Long,FarmData>,Map<Long,CropData>> simpleEntry = extractPlantedData(plantedList);
        simpleEntry = extractHarvestedData(harvestedList,simpleEntry.getKey(),simpleEntry.getValue());
        return ReportData.builder()
                .season(seasonRepository.findById(seasonId).get().getSeasonName())
                .farmData(simpleEntry.getKey().values().stream().toList())
                .cropData(simpleEntry.getValue().values().stream().toList())
                .build();
    }

    private AbstractMap.SimpleEntry extractPlantedData(List<Plant> plantedList){
        Map<Long, FarmData> farmVsData = new HashMap<>();
        Map<Long, CropData> cropVsData = new HashMap<>();
        plantedList.forEach(obj -> {
            //Grouping farm data
            long farmId = obj.getFarm().getFarmId();
            if (farmVsData.containsKey(farmId)) {
                FarmData current = farmVsData.get(farmId);
                current.setExpectedYield(current.getExpectedYield() + obj.getExpectedYield());
                farmVsData.replace(farmId, current);
            } else
                farmVsData.put(farmId, FarmData.builder().farmName(obj.getFarm().getFarmName()
                ).expectedYield(obj.getExpectedYield()).build());
            //Grouping crop data
            long cropId = obj.getCrop().getCropId();
            if (cropVsData.containsKey(farmId)) {
                CropData current = cropVsData.get(farmId);
                current.setExpectedYield(current.getExpectedYield() + obj.getExpectedYield());
                cropVsData.replace(farmId, current);
            } else
                cropVsData.put(cropId, CropData.builder().crop(obj.getCrop().getCropName()
                ).expectedYield(obj.getExpectedYield()).build());
        });
        return new AbstractMap.SimpleEntry<>(farmVsData,cropVsData);
    }

    private AbstractMap.SimpleEntry extractHarvestedData(List<Harvest> harvestedList, Map<Long, FarmData> farmVsData,
                                                             Map<Long, CropData> cropVsData){
        harvestedList.forEach(obj -> {
            long farmId = obj.getFarm().getFarmId();
            //Groupinh farm data
            if (farmVsData.containsKey(farmId)) {
                FarmData current = farmVsData.get(farmId);
                current.setActualYield(current.getActualYield() + obj.getActualYield());
                farmVsData.replace(farmId, current);
            } else
                farmVsData.put(farmId, FarmData.builder().farmName(obj.getFarm().getFarmName()
                ).actualYield(obj.getActualYield()).build());

            //Grouping crop data
            long cropId = obj.getFarm().getFarmId();
            if (cropVsData.containsKey(farmId)) {
                CropData current = cropVsData.get(farmId);
                current.setActualYield(current.getActualYield() + obj.getActualYield());
                cropVsData.replace(farmId, current);
            } else
                cropVsData.put(cropId, CropData.builder().crop(obj.getFarm().getFarmName()
                ).actualYield(obj.getActualYield()).build());
        });
        return new AbstractMap.SimpleEntry<>(farmVsData,cropVsData);

    }
}
