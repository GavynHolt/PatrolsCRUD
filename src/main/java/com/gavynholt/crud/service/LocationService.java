package com.gavynholt.crud.service;

import com.gavynholt.crud.entity.Location;
import com.gavynholt.crud.entity.PostOrder;

import java.util.List;

public interface LocationService {

    public List<Location> getLocations();

    public Location getLocationById(int locationId);

    public void addNewLocation(Location locationToAdd);

    public Location updateLocation(Location locationToUpdate);

    public void deleteLocation(int locationId);

    public List<PostOrder> getPostOrders(int locationId);

    public PostOrder getPostOrderById(int postOrderId);

    public void addPostOrder(PostOrder postOrderToAdd);

    public void updatedPostOrder(PostOrder postOrderToUpdate);

    public void deletePostOrder(int postOrderId);
}
