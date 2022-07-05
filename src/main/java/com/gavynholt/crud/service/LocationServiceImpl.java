package com.gavynholt.crud.service;

import com.gavynholt.crud.dao.LocationDAO;
import com.gavynholt.crud.entity.Location;
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
}
