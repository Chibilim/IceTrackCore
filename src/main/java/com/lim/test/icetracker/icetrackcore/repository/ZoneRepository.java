package com.lim.test.icetracker.icetrackcore.repository;

import com.lim.test.icetracker.icetrackcore.model.Zone;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//TODO lim : Implement this!

@Repository
public class ZoneRepository {
    private Zone zone = new Zone(0, false);

    public void lock(int zoneId) {
        zone = new Zone(zoneId, true);
    }

    public Optional<Zone> getZone(int zoneId) {
        return Optional.of(zone);
    }
}
