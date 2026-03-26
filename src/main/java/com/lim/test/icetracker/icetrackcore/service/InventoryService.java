package com.lim.test.icetracker.icetrackcore.service;

import com.lim.test.icetracker.icetrackcore.model.event.IncidentEvent;
import com.lim.test.icetracker.icetrackcore.repository.ZoneRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryService {

    private final ZoneRepository zoneRepository;

    public void OnIncidentEvent(IncidentEvent event){
        String sensorId = event.sensorId();
        int zoneId = getZoneIdBySensorId(sensorId);
        lockZone(zoneId);
    }

    private int getZoneIdBySensorId(String sensorId){
        return 0;
    }

    private void lockZone(int zoneId){
        zoneRepository.lock(zoneId);
    }
}
