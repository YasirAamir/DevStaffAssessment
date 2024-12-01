package com.devstaff.assessment.controller;

import com.devstaff.assessment.dto.CreatePlantRecord;
import com.devstaff.assessment.service.PlantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;

class PlantControllerTest {

    @Mock
    private PlantService plantService;

    @InjectMocks
    private PlantController plantController;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(plantController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void createPlantRecord_ShouldReturnNoContent() throws Exception {
        CreatePlantRecord createPlantRecord = new CreatePlantRecord(1,1,1,100,50);
        mockMvc.perform(post("/plant")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(createPlantRecord)))
                .andExpect(status().isNoContent());
        verify(plantService, times(1)).createPlantRecord(any(CreatePlantRecord.class));
    }
}
