package com.gavynholt.crud.rest;

import com.gavynholt.crud.entity.Location;
import com.gavynholt.crud.entity.PostOrder;
import com.gavynholt.crud.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {
        "http://localhost:4200",
        "https://patrol.staging.rspndr.io",
        "https://patrol.rspndr.io"
})
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

        Location location = locationService.getLocationById(locationId);

        if (location == null) {
            throw new NotFoundException("Location not found with ID: " + locationId);
        }

        return location;
    }

    @PostMapping("locations")
    public Location addNewLocation(@RequestBody Location locationToAdd) {

        locationToAdd.setId(0);
        locationService.addNewLocation(locationToAdd);

        return locationToAdd;
    }

    @PutMapping("locations")
    public Location updateLocation(@RequestBody Location locationToUpdate) {

        locationService.updateLocation(locationToUpdate);

        return locationService.getLocationById(locationToUpdate.getId());
    }

    @DeleteMapping("locations/{locationId}")
    public String deleteLocation(@PathVariable(value="locationId") int locationId) {

        Location location = locationService.getLocationById(locationId);

        if (location == null) {
            throw new NotFoundException("Location not found with ID: " + locationId);
        }

        locationService.deleteLocation(locationId);

        return "Successfully deleted location with id: " + locationId;
    }

    @GetMapping("postorders/location/{locationId}")
    public List<PostOrder> getPostOrders(@PathVariable(value="locationId") int locationId) {

        List<PostOrder> postOrders = locationService.getPostOrders(locationId);

        if ( postOrders == null) {
            throw new NotFoundException("Location not found with ID: " + locationId);
        }

        return postOrders;
    }

    @GetMapping("postorders/{postOrderId}")
    public PostOrder getPostOrder(@PathVariable(value="postOrderId") int postOrderId) {

        PostOrder postOrder = locationService.getPostOrderById(postOrderId);

        if (postOrder == null) {
            throw new NotFoundException("Post Order not found with ID: " + postOrderId);
        }

        return postOrder;
    }

    @PostMapping("postorders/location/{locationId}")
    public PostOrder addPostOrderToLocation(@PathVariable(value="locationId") int locationId, @RequestBody PostOrder postOrderToAdd) {

        postOrderToAdd.setId(0);

        Location postOrderLocation = locationService.getLocationById(locationId);

        if (postOrderLocation == null) {
            throw new NotFoundException("Location not found with ID: " + locationId);
        }

        postOrderToAdd.setLocation(postOrderLocation);

        locationService.addPostOrder(postOrderToAdd);

        return postOrderToAdd;
    }

    @PutMapping("postorders/{locationId}")
    public PostOrder updatePostOrder(@PathVariable(value="locationId") int locationId, @RequestBody PostOrder postOrderToUpdate) {

        Location postOrderLocation = locationService.getLocationById(locationId);

        if (postOrderLocation == null) {
            throw new NotFoundException("Location not found with ID: " + locationId);
        }

        postOrderToUpdate.setLocation(postOrderLocation);

        locationService.updatedPostOrder(postOrderToUpdate);

        return postOrderToUpdate;
    }

    @DeleteMapping("postorders/{postOrderId}")
    public ResponseEntity<String> deletePostorder(@PathVariable(value="postOrderId") int postOrderId) {

        PostOrder postOrder = locationService.getPostOrderById(postOrderId);

        if (postOrder == null) {
            throw new NotFoundException("Post Order not found with ID: " + postOrderId);
        }

        locationService.deletePostOrder(postOrderId);

        return new ResponseEntity<String>("Successfully delete post order with id: " + postOrderId, HttpStatus.OK);
    }
}
