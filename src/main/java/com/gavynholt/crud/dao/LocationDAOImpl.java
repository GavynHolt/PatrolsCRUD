package com.gavynholt.crud.dao;

import com.gavynholt.crud.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class LocationDAOImpl implements LocationDAO {

    private EntityManager entityManager;

    @Autowired
    public LocationDAOImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<Location> getLocations() {

        Query theQuery = entityManager.createQuery("from Location");

        List<Location> locations = theQuery.getResultList();

        return locations;
    }

    @Override
    public Location getLocationById(int locationId) {

        return entityManager.find(Location.class, locationId);
    }

    @Override
    public void addNewLocation(Location locationToAdd) {

        Location dbLocation = entityManager.merge(locationToAdd);

        locationToAdd.setId(dbLocation.getId());
    }
}
