package com.devstaff.assessment.controller;

import com.devstaff.assessment.dto.CropData;
import com.devstaff.assessment.dto.FarmData;
import com.devstaff.assessment.dto.ReportData;
import com.devstaff.assessment.service.RecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.Mockito.verify;

class ReportControllerTest {

    @Mock
    private RecordService recordService;

    @InjectMocks
    private ReportController reportController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(reportController).build();
    }

    @Test
    void getRecordByFarm_ShouldReturnReportData() throws Exception {
        ReportData mockReportData = new ReportData("Spring 2024", List.of(FarmData.builder()
                .farmName("GreenField")
                .actualYield(100)
                .expectedYield(120).build())
                ,
                List.of(CropData.builder()
                        .crop("Corn")
                        .actualYield(100)
                        .actualYield(100).build()
                ));
        when(recordService.getReportFromFarm(anyLong())).thenReturn(mockReportData);

        mockMvc.perform(get("/report/season/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.season").value("Spring 2024"));

        verify(recordService).getReportFromFarm(1L);
    }
}
