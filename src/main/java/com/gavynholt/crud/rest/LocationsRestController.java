package com.gavynholt.crud.rest;

import com.gavynholt.crud.entity.Location;
import com.gavynholt.crud.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
public class LocationsRestController {

    @Autowired
    private LocationService locationService;

    private List<Location> locations;

    @PostConstruct
    public void loadData() {

        locations = new ArrayList<>();

        locations.add(new Location("1", "Glory Hole Doughnuts"));
        locations.add(new Location("2", "Ed's Real Scoop"));
        locations.add(new Location("3", "Sanremo Bakery"));
        locations.add(new Location("4", "Tom's Dairy Freeze"));
        locations.add(new Location("5", "Maurya East Indian Roti"));
    }

    @GetMapping("/locations")
    public List<Location> getLocations() {

        return locationService.getLocations();
    }

    @GetMapping("/locations/{locationId}")
    public Location getLocationById(@PathVariable String locationId) {

        return locations.stream().filter(location -> locationId.equals(location.getId())).findFirst().orElse((null));
    }

    @PostMapping("locations")
    public Location addNewLocation(@RequestBody Location locationToAdd) {
        locations.add(locationToAdd);

        return locationToAdd;
    }
}
