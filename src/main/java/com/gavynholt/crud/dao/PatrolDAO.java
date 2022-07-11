package com.gavynholt.crud.dao;

import com.gavynholt.crud.entity.Patrol;

import java.util.List;

public interface PatrolDAO {

    public List<Patrol> getScheduledPatrols();
}
