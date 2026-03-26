package com.lim.test.icetracker.icetrackcore.publisher;

import com.lim.test.icetracker.icetrackcore.model.event.IEvent;

public interface IEventPublisher {
    void publish(IEvent event);
}
