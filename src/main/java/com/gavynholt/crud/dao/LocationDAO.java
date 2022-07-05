package com.gavynholt.crud.dao;

import com.gavynholt.crud.entity.Location;

import java.util.List;

public interface LocationDAO {

    public List<Location> getLocations();

    public Location getLocationById(int locationId);

    public void addNewLocation(Location locationToAdd);
}
