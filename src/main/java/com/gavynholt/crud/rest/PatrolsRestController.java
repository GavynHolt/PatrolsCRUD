package com.gavynholt.crud.rest;

import com.gavynholt.crud.entity.Patrol;
import com.gavynholt.crud.service.PatrolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class PatrolsRestController {

    @Autowired
    private PatrolService patrolService;

    @GetMapping("patrols/scheduled")
    public List<Patrol> getScheduledPatrols() {

        return patrolService.getScheduledPatrols();
    }

}
