package com.gavynholt.crud.rest;

import com.gavynholt.crud.entity.Patrol;
import com.gavynholt.crud.service.PatrolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {
        "http://localhost:4200",
        "https://patrol.staging.rspndr.io",
        "https://patrol.rspndr.io"
})
@RequestMapping("api")
public class PatrolsRestController {

    @Autowired
    private PatrolService patrolService;

    @GetMapping("patrols/scheduled")
    public List<Patrol> getScheduledPatrols() {

        return patrolService.getScheduledPatrols();
    }

    @GetMapping("patrols/{patrolId}")
    public Patrol getPatrolById(@PathVariable(value="patrolId") int patrolId) {

        Patrol patrol = patrolService.getPatrolById(patrolId);

        if (patrol == null) {
            throw new NotFoundException("Patrol not found with ID: " + patrolId);
        }

        return patrol;
    }

    @PostMapping("patrols")
    public List<Patrol> addNewPatrol(@RequestBody List<Patrol> patrolsToAdd) {

        patrolsToAdd.forEach(patrolToAdd -> patrolToAdd.setId(0));

        patrolService.addNewPatrols(patrolsToAdd);

        return patrolsToAdd;
    }

    @DeleteMapping("patrols/{patrolId}")
    public String deletePatrol(@PathVariable(value="patrolId") int patrolId) {

        Patrol patrol = patrolService.getPatrolById(patrolId);

        if (patrol == null) {
            throw new NotFoundException("Patrol not found with ID: " + patrolId);
        }

        patrolService.deletePatrol(patrolId);

        return "Successfully deleted patrol with id: " + patrolId;
    }

}
