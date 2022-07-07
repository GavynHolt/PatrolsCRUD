package com.gavynholt.crud.rest;

import com.gavynholt.crud.entity.Location;
import com.gavynholt.crud.entity.PostOrder;
import com.gavynholt.crud.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api")
public class LocationsRestController {

    @Autowired
    private LocationService locationService;

    @GetMapping("locations")
    public List<Location> getLocations() {

        return locationService.getLocations();
    }

    @GetMapping("locations/{locationId}")
    public Location getLocationById(@PathVariable int locationId) {

        return locationService.getLocationById(locationId);
    }

    @PostMapping("locations")
    public Location addNewLocation(@RequestBody Location locationToAdd) {

        locationToAdd.setId(0);

        locationService.addNewLocation(locationToAdd);

        return locationToAdd;
    }

    @GetMapping("postorders/location/{locationId}")
    public List<PostOrder> getPostOrders(@PathVariable(value="locationId") int locationId) {

        return locationService.getPostOrders(locationId);
    }

    @GetMapping("postorders/{postOrderId}")
    public PostOrder getPostOrder(@PathVariable(value="postOrderId") int postOrderId) {

        return locationService.getPostOrderById(postOrderId);
    }

    @PostMapping("postorders/location/{locationId}/")
    public PostOrder addPostOrderToLocation(@PathVariable(value="locationId") int locationId, @RequestBody PostOrder postOrderToAdd) {

        postOrderToAdd.setId(0);

        Location postOrderLocation = locationService.getLocationById(locationId);
        postOrderToAdd.setLocation(postOrderLocation);

        locationService.addPostOrder(postOrderToAdd);

        return postOrderToAdd;
    }

    @PutMapping("postorders/{locationId}")
    public PostOrder updatePostOrder(@PathVariable(value="locationId") int locationId, @RequestBody PostOrder postOrderToUpdate) {

        Location postOrderLocation = locationService.getLocationById(locationId);
        postOrderToUpdate.setLocation(postOrderLocation);

        locationService.updatedPostOrder(postOrderToUpdate);

        return postOrderToUpdate;
    }

    @DeleteMapping("postorders/{postOrderId}")
    public String deletePostorder(@PathVariable(value="postOrderId") int postOrderId) {

        locationService.deletePostOrder(postOrderId);

        return "Successfully delete post order with id: " + postOrderId;
    }
}
