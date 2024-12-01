package com.devstaff.assessment.controller;

import com.devstaff.assessment.dto.CreateHarvestRecord;
import com.devstaff.assessment.dto.CreatePlantRecord;
import com.devstaff.assessment.service.HarvestService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/harvest")
public class HarvestController {
    private final HarvestService harvestService;

    @Autowired
    public HarvestController(HarvestService harvestService) {
        this.harvestService = harvestService;
    }

    @Operation(summary = "Created a new harvest record", description = "Creates a record of the harvest crop in the season for the mentioned farm")
    @PostMapping
    public ResponseEntity createHarvestRecord(@RequestBody @Valid CreateHarvestRecord createHarvestRecord){
        harvestService.createHarvestRecord(createHarvestRecord);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
