package com.lim.test.icetracker.icetrackcore.controller;

import com.lim.test.icetracker.icetrackcore.model.dto.TelemetryDto;
import com.lim.test.icetracker.icetrackcore.service.TelemetryPushService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sensors")
@RequiredArgsConstructor
public class SensorsController {

    private final TelemetryPushService telemetryPushService;

    @PostMapping("/{sensorId}/telemetry")
    public ResponseEntity<Void> pushTelemetry(@PathVariable String sensorId, @RequestBody TelemetryDto telemetry) {
        telemetryPushService.pushTelemetryAsync(sensorId, telemetry.temp(), telemetry.humidity());
        return ResponseEntity.accepted().build();
    }
}
