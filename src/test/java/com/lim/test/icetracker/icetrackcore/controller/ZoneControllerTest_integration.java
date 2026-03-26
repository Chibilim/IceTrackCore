package com.lim.test.icetracker.icetrackcore.controller;

import com.lim.test.icetracker.icetrackcore.model.Zone;
import com.lim.test.icetracker.icetrackcore.repository.ZoneRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ZoneControllerTest_integration {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ZoneRepository zoneRepository;

    @Test
    public void getZoneHealth_ShouldCallGetZoneInRepository() throws Exception {
        when(zoneRepository.getZone(anyInt())).thenReturn(Optional.of(new Zone(12, false)));

        var zoneId = 12;
        mockMvc.perform(get("/zones/{zoneId}/health", zoneId))
                .andExpect(status().isOk());

        verify(zoneRepository, times(1)).getZone(zoneId);
    }
}
