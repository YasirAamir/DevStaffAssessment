package com.devstaff.assessment.service;

import com.devstaff.assessment.dto.CreatePlantRecord;
import com.devstaff.assessment.entity.Plant;
import com.devstaff.assessment.repository.PlantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlantService {
    private final PlantRepository plantRepository;

    @Autowired
    public PlantService(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    public void createPlantRecord(CreatePlantRecord createPlantRecord){
        plantRepository.save(Plant.createPlantEntity(createPlantRecord));
    }
}
