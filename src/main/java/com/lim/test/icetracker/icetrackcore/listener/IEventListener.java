package com.lim.test.icetracker.icetrackcore.listener;

import com.lim.test.icetracker.icetrackcore.model.event.IEvent;

public interface IEventListener {
    void onEvent(IEvent event);
}
