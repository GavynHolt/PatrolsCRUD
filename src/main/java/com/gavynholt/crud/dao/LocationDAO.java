package com.gavynholt.crud.dao;

import com.gavynholt.crud.entity.Location;
import com.gavynholt.crud.entity.PostOrder;

import java.util.List;

public interface LocationDAO {

    public List<Location> getLocations();

    public Location getLocationById(int locationId);

    public void addNewLocation(Location locationToAdd);

    public void deleteLocation(int locationId);

    public List<PostOrder> getPostOrders(int locationId);

    public PostOrder getPostOrderById(int postOrderId);

    public void addPostOrder(PostOrder postOrderToAdd);

    public void updatePostOrder(PostOrder postOrderToUpdate);

    public void deletePostOrder(int postOrderId);
}
