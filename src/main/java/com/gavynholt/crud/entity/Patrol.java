package com.gavynholt.crud.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="patrol")
public class Patrol {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="patrol_id")
    private int id;

    @Column(name="patrol_name")
    private String name;

    @Column(name="amount")
    private int amount;

    @JsonManagedReference
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="patrol_date_range_id")
    private PatrolDateRange dateRange;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="patrol_time_window_id")
    private PatrolTimeWindow timeWindow;

    @ElementCollection
    @CollectionTable(
            name="patrol_recurring",
            joinColumns=@JoinColumn(name="patrol_id")
    )
    @Column(name="day")
    private List<String> recurring;

    @Column(name="post_order_notes")
    private String postOrderNotes;

    @Column(name="patrol_check_notes")
    private String patrolCheckNotes;

    public Patrol() {
    }

    public Patrol(String name, String postOrderNotes) {
        this.name = name;
        this.postOrderNotes = postOrderNotes;
    }

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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public PatrolDateRange getDateRange() {
        return dateRange;
    }

    public void setDateRange(PatrolDateRange dateRange) {
        this.dateRange = dateRange;
    }

    public PatrolTimeWindow getTimeWindow() {
        return timeWindow;
    }

    public void setTimeWindow(PatrolTimeWindow timeWindow) {
        this.timeWindow = timeWindow;
    }

    public List<String> getRecurring() {
        return recurring;
    }

    public void setRecurring(List<String> recurring) {
        this.recurring = recurring;
    }

    public String getPostOrderNotes() {
        return postOrderNotes;
    }

    public void setPostOrderNotes(String postOrderNotes) {
        this.postOrderNotes = postOrderNotes;
    }

    public String getPatrolCheckNotes() {
        return patrolCheckNotes;
    }

    public void setPatrolCheckNotes(String patrolCheckNotes) {
        this.patrolCheckNotes = patrolCheckNotes;
    }
}
