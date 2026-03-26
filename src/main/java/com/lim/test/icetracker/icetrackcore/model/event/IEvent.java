package com.lim.test.icetracker.icetrackcore.model.event;

public sealed interface IEvent permits TemperatureEvent, IncidentEvent {
}
