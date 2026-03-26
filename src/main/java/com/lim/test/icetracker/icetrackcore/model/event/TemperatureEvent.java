package com.lim.test.icetracker.icetrackcore.model.event;

public record TemperatureEvent(String sensorId, double temperature) implements IEvent {
}
