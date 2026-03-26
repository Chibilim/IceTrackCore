package com.lim.test.icetracker.icetrackcore.controller;


import com.lim.test.icetracker.icetrackcore.repository.ZoneRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SensorControllerTest_integration {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ZoneRepository zoneRepository;

    @Test
    void pushSensorTelemetry_shouldLock_whenTemperatureUpTo4() throws Exception {
        for (int i = 0; i < 4; i++) {
            mockMvc.perform(
                            post("/sensors/3/telemetry")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content("{\"temp\": 25.0, \"humidity\": 50}")
                    )
                    .andExpect(status().isAccepted());
        }

        verify(zoneRepository, timeout(1000).times(1)).lock(0);
    }
}
