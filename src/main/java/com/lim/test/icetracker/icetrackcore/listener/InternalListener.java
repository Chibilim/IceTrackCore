package com.lim.test.icetracker.icetrackcore.listener;


import com.lim.test.icetracker.icetrackcore.model.event.IEvent;
import com.lim.test.icetracker.icetrackcore.model.event.IncidentEvent;
import com.lim.test.icetracker.icetrackcore.model.event.TemperatureEvent;
import com.lim.test.icetracker.icetrackcore.service.InventoryService;
import com.lim.test.icetracker.icetrackcore.service.QualityService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class InternalListener implements IEventListener {

    public final QualityService qualityService;
    public final InventoryService inventoryService;

    @Override
    @EventListener
    public void onEvent(IEvent event) {

        switch (event) {
            case TemperatureEvent e -> Thread.startVirtualThread(()->qualityService.onTemperatureEvent(e));
            case IncidentEvent e -> Thread.startVirtualThread(()->inventoryService.OnIncidentEvent(e));
        }
    }
}
