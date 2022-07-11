package com.gavynholt.crud.service;

import com.gavynholt.crud.entity.Patrol;

import java.util.List;

public interface PatrolService {

    public List<Patrol> getScheduledPatrols();
}
