package com.devstaff.assessment.service;

import com.devstaff.assessment.dto.CreateHarvestRecord;
import com.devstaff.assessment.entity.Harvest;
import com.devstaff.assessment.repository.HarvestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HarvestService {
    private final HarvestRepository harvestRepository;

    @Autowired
    public HarvestService(HarvestRepository harvestRepository) {
        this.harvestRepository = harvestRepository;
    }

    public void createHarvestRecord(CreateHarvestRecord createHarvestRecord){
        harvestRepository.save(Harvest.createHarvestEntity(createHarvestRecord));
    }
}
