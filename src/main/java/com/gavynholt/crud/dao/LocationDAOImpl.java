package com.gavynholt.crud.dao;

import com.gavynholt.crud.entity.Location;
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

        entityManager.merge(postOrderToUpdate);
    }

    @Override
    public void deletePostOrder(int postOrderId) {

        PostOrder postOrderToDelete = entityManager.find(PostOrder.class, postOrderId);

        entityManager.remove(postOrderToDelete);
    }
}
