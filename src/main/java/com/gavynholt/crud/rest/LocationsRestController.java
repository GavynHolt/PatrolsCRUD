package com.gavynholt.crud.rest;

import com.gavynholt.crud.entity.Location;
import com.gavynholt.crud.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class LocationsRestController {

    @Autowired
    private LocationService locationService;

    @GetMapping("/locations")
    public List<Location> getLocations() {

        return locationService.getLocations();
    }

    @GetMapping("/locations/{locationId}")
    public Location getLocationById(@PathVariable int locationId) {

        return locationService.getLocationById(locationId);
    }

    @PostMapping("locations")
    public Location addNewLocation(@RequestBody Location locationToAdd) {

        locationToAdd.setId(0);

        locationService.addNewLocation(locationToAdd);

        return locationToAdd;
    }
}
