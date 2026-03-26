package com.lim.test.icetracker.icetrackcore.controller;

import com.lim.test.icetracker.icetrackcore.service.TelemetryPushService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SensorsController.class)
class SensorsControllerTest_unit {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TelemetryPushService telemetryPushService;

    @Test
    void pushTelemetry_ShouldAcceptTelemetry() throws Exception {
        mockMvc.perform(
                        post("/sensors/3/telemetry")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"temp\": 25.0, \"humidity\": 50}")
                )
                .andExpect(status().isAccepted());
    }

}