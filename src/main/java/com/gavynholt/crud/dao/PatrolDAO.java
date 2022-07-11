package com.gavynholt.crud.dao;

import com.gavynholt.crud.entity.Patrol;

import java.util.List;

public interface PatrolDAO {

    public List<Patrol> getScheduledPatrols();

    public void addNewPatrols(List<Patrol> patrolsToAdd);

    public void deletePatrol(int patrolId);

}
