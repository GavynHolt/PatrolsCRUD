package com.gavynholt.crud.service;

import com.gavynholt.crud.entity.Patrol;

import java.util.List;

public interface PatrolService {

    public List<Patrol> getScheduledPatrols();

    public Patrol getPatrolById(int patrolId);

    public void addNewPatrols(List<Patrol> patrolsToAdd);

    public void deletePatrol(int patrolId);
}
