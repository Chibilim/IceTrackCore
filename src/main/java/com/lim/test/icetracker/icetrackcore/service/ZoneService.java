package com.lim.test.icetracker.icetrackcore.service;

import com.lim.test.icetracker.icetrackcore.model.Zone;
import com.lim.test.icetracker.icetrackcore.repository.ZoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ZoneService {

    private final ZoneRepository zoneRepository;

    public Optional<Zone> getZoneInfo(int zoneId) {
        return zoneRepository.getZone(zoneId);
    }
}
