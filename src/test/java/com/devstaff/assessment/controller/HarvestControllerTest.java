package com.devstaff.assessment.controller;

import com.devstaff.assessment.dto.CreateHarvestRecord;
import com.devstaff.assessment.service.HarvestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class HarvestControllerTest {

    @Mock
    private HarvestService harvestService;

    @InjectMocks
    private HarvestController harvestController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        // Initialize mocks and set up MockMvc
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(harvestController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void createHarvestRecord_ShouldReturnNoContent() throws Exception {
        CreateHarvestRecord createHarvestRecord = new CreateHarvestRecord(1,1,1,90);
        mockMvc.perform(post("/harvest")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(createHarvestRecord)))  // Mock JSON payload
                .andExpect(status().isNoContent());  // Expect HTTP 204 (No Content)

    }
}