package com.gavynholt.crud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name="patrol_date_range")
public class PatrolDateRange {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="patrol_date_range_id")
    private int id;

    @Column(name="start_date")
    private String start;

    @Column(name="end_date")
    private String end;

    @Column(name="no_end_date_selected")
    private Boolean noEndDateSelected;

    @JsonBackReference
    @OneToOne(mappedBy="dateRange",
            cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Patrol patrol;

    public PatrolDateRange() {

    }

    public PatrolDateRange(String start, String end) {
        this.start = start;
        this.end = end;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Boolean getNoEndDateSelected() {
        return noEndDateSelected;
    }

    public void setNoEndDateSelected(Boolean noEndDateSelected) {
        this.noEndDateSelected = noEndDateSelected;
    }

    public Patrol getPatrol() {
        return patrol;
    }

    public void setPatrol(Patrol patrol) {
        this.patrol = patrol;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DateRange{");
        sb.append("id=").append(id);
        sb.append(", start='").append(start).append('\'');
        sb.append(", end='").append(end).append('\'');
        sb.append(", patrol=").append(patrol);
        sb.append('}');
        return sb.toString();
    }
}
