package com.lim.test.icetracker.icetrackcore.controller;

import com.lim.test.icetracker.icetrackcore.model.Zone;
import com.lim.test.icetracker.icetrackcore.model.dto.ZoneHealthDto;
import com.lim.test.icetracker.icetrackcore.service.ZoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/zones")
@RequiredArgsConstructor
public class ZoneController {

    private final ZoneService zoneService;

    @GetMapping("/{zoneId}/health")
    public ZoneHealthDto getZoneHealth(@PathVariable int zoneId) {
        return zoneToZoneHealthDto(
                zoneService.getZoneInfo(zoneId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Zone " + zoneId + " not found"))
        );
    }


    private static ZoneHealthDto zoneToZoneHealthDto(Zone zone) {
        return new ZoneHealthDto(zone.locked());
    }
}
