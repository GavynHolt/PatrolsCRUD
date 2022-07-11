package com.gavynholt.crud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="post_order")
public class PostOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_order_id")
    private int id;

    @Column(name="name")
    private String name;

    @ElementCollection
    @CollectionTable(
            name="step",
            joinColumns=@JoinColumn(name="post_order_id")
    )
    @Column(name="name")
    private List<String> steps;

    @JsonManagedReference
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="date_range_id")
    private DateRange dateRange;

    @ElementCollection
    @CollectionTable(
            name="recurring",
            joinColumns=@JoinColumn(name="post_order_id")
    )
    @Column(name="day")
    private List<String> recurring;

    @JsonManagedReference
    @OneToMany(mappedBy="postOrder",
            cascade={CascadeType.ALL}
    )
    private List<PatrolCheck> patrolChecks;

    @Column(name="notes")
    private String notes;

    @JsonBackReference
    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="location_id")
    private Location location;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public void setDateRange(DateRange dateRange) {
        this.dateRange = dateRange;
    }

    public List<String> getRecurring() {
        return recurring;
    }

    public void setRecurring(List<String> recurring) {
        this.recurring = recurring;
    }

    public List<PatrolCheck> getPatrolChecks() {
        return patrolChecks;
    }

    public void setPatrolChecks(List<PatrolCheck> patrolChecks) {
        this.patrolChecks = patrolChecks;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PostOrder{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", steps=").append(steps);
        sb.append(", dateRange=").append(dateRange);
        sb.append(", recurring=").append(recurring);
        sb.append(", patrolChecks=").append(patrolChecks);
        sb.append(", notes='").append(notes).append('\'');
        sb.append(", location=").append(location);
        sb.append('}');
        return sb.toString();
    }
}
