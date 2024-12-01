package com.devstaff.assessment.controller;

import com.devstaff.assessment.dto.ReportData;
import com.devstaff.assessment.service.RecordService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class ReportController {

    private final RecordService recordService;

    @Autowired
    public ReportController(RecordService recordService) {
        this.recordService = recordService;
    }

    @Operation(summary = "Get record by farm", description = "Gets record for a season based on the farm")
    @GetMapping("/season/{seasonId}")
    public ResponseEntity<ReportData> getRecordByFarm(@PathVariable long seasonId){
        return new ResponseEntity(recordService.getReportFromFarm(seasonId), HttpStatusCode.valueOf(200));
    }

}
