package com.lim.test.icetracker.icetrackcore.publisher;

import com.lim.test.icetracker.icetrackcore.model.event.IEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InternalPublisher implements IEventPublisher{

    private final ApplicationEventPublisher publisher;

    @Override
    public void publish(IEvent event) {
        publisher.publishEvent(event);
    }
}
