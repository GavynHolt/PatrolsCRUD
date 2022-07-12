package com.gavynholt.crud.rest;

import com.gavynholt.crud.entity.Patrol;
import com.gavynholt.crud.service.PatrolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("patrols")
    public List<Patrol> addNewPatrol(@RequestBody List<Patrol> patrolsToAdd) {

        patrolsToAdd.forEach(patrolToAdd -> patrolToAdd.setId(0));

        patrolService.addNewPatrols(patrolsToAdd);

        return patrolsToAdd;
    }

    @DeleteMapping("patrols/{patrolId}")
    public String deletePatrol(@PathVariable(value="patrolId") int patrolId) {

        patrolService.deletePatrol(patrolId);

        return "Successfully deleted patrol with id: " + patrolId;
    }

}
