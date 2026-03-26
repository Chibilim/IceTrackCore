package com.lim.test.icetracker.icetrackcore.controller;

import com.lim.test.icetracker.icetrackcore.model.Zone;
import com.lim.test.icetracker.icetrackcore.service.ZoneService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ZoneController.class)
class ZoneControllerTest_unit {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ZoneService zoneService;

    @Test
    public void getZoneHealth_ShouldReturnZoneHealthWithNotLocked_whenZoneIsNotLocked() throws Exception {
        when(zoneService.getZoneInfo(0)).thenReturn(Optional.of(new Zone(0, false)));

        mockMvc.perform(get("/zones/0/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.locked").value(false));
    }

    @Test
    public void getZoneHealth_ShouldReturnZoneHealthWithLocked_whenZoneIsLocked() throws Exception {
        when(zoneService.getZoneInfo(0)).thenReturn(Optional.of(new Zone(0, true)));

        mockMvc.perform(get("/zones/0/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.locked").value(true));
    }

    @Test
    public void getZoneHealth_ShouldReturn404_whenZoneIsMissing() throws Exception {
        when(zoneService.getZoneInfo(0)).thenReturn(Optional.empty());

        mockMvc.perform(get("/zones/0/health"))
                .andExpect(status().isNotFound());
    }

}