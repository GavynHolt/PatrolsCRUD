package com.gavynholt.crud.dao;

import com.gavynholt.crud.entity.Location;
import com.gavynholt.crud.entity.Patrol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class PatrolDAOImpl implements  PatrolDAO {

    EntityManager entityManager;

    @Autowired
    public PatrolDAOImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    public List<Patrol> getScheduledPatrols() {
        Query theQuery = entityManager.createQuery("from Patrol");

        List<Patrol> scheduledPatrols = theQuery.getResultList();

        return scheduledPatrols;
    }

    @Override
    public Patrol getPatrolById(int patrolId) {

        return entityManager.find(Patrol.class, patrolId);
    }

    @Override
    public void addNewPatrols(List<Patrol> patrolsToAdd) {

        patrolsToAdd.forEach(patrolToAdd -> {
            Patrol dbPatrol = entityManager.merge(patrolToAdd);
            patrolToAdd.setId(dbPatrol.getId());
        });
    }

    @Override
    public void deletePatrol(int patrolId) {

        Patrol patrolToDelete = entityManager.find(Patrol.class, patrolId);

        entityManager.remove(patrolToDelete);
    }
}
