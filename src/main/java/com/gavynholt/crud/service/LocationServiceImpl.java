package com.gavynholt.crud.service;

import com.gavynholt.crud.dao.LocationDAO;
import com.gavynholt.crud.entity.Location;
import com.gavynholt.crud.entity.PostOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationDAO locationDAO;

    @Override
    @Transactional
    public List<Location> getLocations() {

        return locationDAO.getLocations();
    }

    @Override
    @Transactional
    public Location getLocationById(int locationId) {

        return locationDAO.getLocationById(locationId);
    }

    @Override
    @Transactional
    public void addNewLocation(Location locationToAdd) {

        locationDAO.addNewLocation(locationToAdd);
    }

    @Override
    @Transactional
    public void updateLocation(Location locationToUpdate) {

        locationDAO.updateLocation(locationToUpdate);
    }

    @Override
    @Transactional
    public void deleteLocation(int locationId) {

        locationDAO.deleteLocation(locationId);
    }

    @Override
    @Transactional
    public List<PostOrder> getPostOrders(int locationId) {

        return locationDAO.getPostOrders(locationId);
    }

    public PostOrder getPostOrderById(int postOrderId) {

        return locationDAO.getPostOrderById(postOrderId);
    }

    @Override
    @Transactional
    public void addPostOrder(PostOrder postOrderToAdd) {

        locationDAO.addPostOrder(postOrderToAdd);
    }

    @Override
    @Transactional
    public void updatedPostOrder(PostOrder postOrderToUpdate) {

        locationDAO.updatePostOrder(postOrderToUpdate);
    }

    @Override
    @Transactional
    public void deletePostOrder(int postOrderId) {

        locationDAO.deletePostOrder(postOrderId);
    }
}
