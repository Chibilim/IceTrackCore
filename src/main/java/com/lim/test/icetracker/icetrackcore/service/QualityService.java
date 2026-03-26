package com.lim.test.icetracker.icetrackcore.service;

import com.lim.test.icetracker.icetrackcore.model.event.IncidentEvent;
import com.lim.test.icetracker.icetrackcore.model.event.TemperatureEvent;
import com.lim.test.icetracker.icetrackcore.publisher.IEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
@Slf4j
public class QualityService {

    @Value("${icetracker.temperatureIncidentThreshold}")
    private int temperatureIncidentThreshold;

    @Value("${icetracker.consecutiveTemperatureIncidentCount}")
    private int consecutiveTemperatureIncidentCount;

    private final IEventPublisher eventPublisher;

    private final Map<String, AtomicInteger> consecutiveTemperatureIncidentCounter = new ConcurrentHashMap<>();

    public void onTemperatureEvent(TemperatureEvent event) {
        handleTemperatureEvent(event);
    }

    void handleTemperatureEvent(TemperatureEvent event) {
        if (event.temperature() <= temperatureIncidentThreshold) {
            consecutiveTemperatureIncidentCounter.computeIfAbsent(event.sensorId(), k -> new AtomicInteger(0)).set(0);
            return;
        }

        var count = consecutiveTemperatureIncidentCounter.computeIfAbsent(event.sensorId(), k -> new AtomicInteger(0)).incrementAndGet();
        if (count > consecutiveTemperatureIncidentCount) {
            log.warn("Temperature incident detected for sensor {}", event.sensorId());

            eventPublisher.publish(new IncidentEvent(event.sensorId()));
        }
    }

    public int getConsecutiveTemperatureIncidentCount(String sensorId) {
        return consecutiveTemperatureIncidentCounter.getOrDefault(sensorId, new AtomicInteger(0)).get();
    }
}
