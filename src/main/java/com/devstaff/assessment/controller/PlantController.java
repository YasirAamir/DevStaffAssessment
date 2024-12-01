package com.devstaff.assessment.controller;

import com.devstaff.assessment.dto.CreatePlantRecord;
import com.devstaff.assessment.service.PlantService;
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
@RequestMapping("/plant")
public class PlantController {
    private final PlantService plantService;

    @Autowired
    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    @Operation(summary = "Created a new planted record", description = "Creates a record of the planted crop in the season for the mentioned farm")
    @PostMapping
    public ResponseEntity createPlantRecord(@RequestBody @Valid CreatePlantRecord createPlantRecord){
        plantService.createPlantRecord(createPlantRecord);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
