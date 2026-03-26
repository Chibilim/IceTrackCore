package com.lim.test.icetracker.icetrackcore.service;

import com.lim.test.icetracker.icetrackcore.model.event.TemperatureEvent;
import com.lim.test.icetracker.icetrackcore.publisher.IEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TelemetryPushService {

    private final IEventPublisher eventPublisher;

    public void pushTelemetryAsync(String sensorId, double temp, double humidity) {
        Thread.startVirtualThread(()-> pushTelemetry(sensorId, temp, humidity));
    }

    public void pushTelemetry(String sensorId, double temp, double humidity) {
        eventPublisher.publish(new TemperatureEvent(sensorId, temp));
    }
}
