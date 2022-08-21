package com.gavynholt.crud.dao;

import antlr.StringUtils;
import com.gavynholt.crud.entity.Location;
import com.gavynholt.crud.entity.PatrolCheck;
import com.gavynholt.crud.entity.PostOrder;
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

    @Override
    public void updateLocation(Location locationToUpdate) {

        Location dbLocation = entityManager.find(Location.class, locationToUpdate.getId());

        if (locationToUpdate.getName() != null && !dbLocation.getName().equals(locationToUpdate.getName())) {
            dbLocation.setName(locationToUpdate.getName());
        }

        if (locationToUpdate.getAddress1() != null && !dbLocation.getAddress1().equals(locationToUpdate.getAddress1())) {
            dbLocation.setAddress1(locationToUpdate.getAddress1());
        }

        if (locationToUpdate.getAddress2() != null && !dbLocation.getAddress2().equals(locationToUpdate.getAddress2())) {
            dbLocation.setAddress2(locationToUpdate.getAddress2());
        }

        if (locationToUpdate.getCity() != null && !dbLocation.getCity().equals(locationToUpdate.getCity())) {
            dbLocation.setCity(locationToUpdate.getCity());
        }

        if (locationToUpdate.getState() != null && !dbLocation.getState().equals(locationToUpdate.getState())) {
            dbLocation.setState(locationToUpdate.getState());
        }

        if (locationToUpdate.getPostalCode() != null && !dbLocation.getPostalCode().equals(locationToUpdate.getPostalCode())) {
            dbLocation.setPostalCode(locationToUpdate.getPostalCode());
        }

        if (locationToUpdate.getCountry() != null && !dbLocation.getCountry().equals(locationToUpdate.getCountry())) {
            dbLocation.setCountry(locationToUpdate.getCountry());
        }

        entityManager.merge(dbLocation);
    }

    @Override
    public void deleteLocation(int locationId) {

        Location locationToDelete = entityManager.find(Location.class, locationId);

        entityManager.remove(locationToDelete);
    }

    @Override
    public List<PostOrder> getPostOrders(int locationId) {

        Query postOrdersQuery = entityManager.createQuery("from PostOrder where location_id=:locationId");
        postOrdersQuery.setParameter("locationId", locationId);

        List<PostOrder> postOrders = postOrdersQuery.getResultList();

        return postOrders;
    }

    @Override
    public PostOrder getPostOrderById(int postOrderId) {

        PostOrder dbPostOrder = entityManager.find(PostOrder.class, postOrderId);

        return dbPostOrder;
    }

    @Override
    public void addPostOrder(PostOrder postOrderToAdd) {

        PostOrder dbPostOrder = entityManager.merge(postOrderToAdd);

        postOrderToAdd.setId(dbPostOrder.getId());
    }

    @Override
    public void updatePostOrder(PostOrder postOrderToUpdate) {
        List<PatrolCheck> patrolChecksToUpdate = postOrderToUpdate.getPatrolChecks();
        List<PatrolCheck> dbPatrolChecks = entityManager.find(PostOrder.class, postOrderToUpdate.getId()).getPatrolChecks();

        // If there is a removed Patrol Check in patrolsChecksToUpdate, remove from DB
        dbPatrolChecks.forEach(patrolCheck -> {
            if (patrolChecksToUpdate.stream().noneMatch((patrolCheck1) -> patrolCheck1.getId() == patrolCheck.getId())) {
                entityManager.remove(patrolCheck);
            }
        });

        entityManager.merge(postOrderToUpdate);
    }

    @Override
    public void deletePostOrder(int postOrderId) {

        PostOrder postOrderToDelete = entityManager.find(PostOrder.class, postOrderId);

        entityManager.remove(postOrderToDelete);
    }
}
