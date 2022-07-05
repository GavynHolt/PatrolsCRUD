package com.gavynholt.crud.service;

import com.gavynholt.crud.entity.Location;

import java.util.List;

public interface LocationService {

    public List<Location> getLocations();

    public Location getLocation(int locationId);
}
