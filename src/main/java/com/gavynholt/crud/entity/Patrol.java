package com.gavynholt.crud.entity;

import javax.persistence.*;

@Entity
@Table(name="patrol")
public class Patrol {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="patrol_id")
    private int id;

    @Column(name="patrol_name")
    private String name;

    @Column(name="notes")
    private String notes;

    public Patrol() {
    }

    public Patrol(String name, String notes) {
        this.name = name;
        this.notes = notes;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
