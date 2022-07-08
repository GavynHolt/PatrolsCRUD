package com.gavynholt.crud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name="patrol")
public class Patrol {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="patrol_id")
    private int id;

    @Column(name="amount")
    private int amount;

    @JsonManagedReference
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="time_window_id")
    private DateRange timeWindow;

    @JsonBackReference
    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="post_order_id")
    private Patrol patrol;

    public Patrol() {
    }

    public Patrol(int amount, DateRange timeWindow) {
        this.amount = amount;
        this.timeWindow = timeWindow;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public DateRange getTimeWindow() {
        return timeWindow;
    }

    public void setTimeWindow(DateRange timeWindow) {
        this.timeWindow = timeWindow;
    }

    public Patrol getPatrol() {
        return patrol;
    }

    public void setPatrol(Patrol patrol) {
        this.patrol = patrol;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Patrol{");
        sb.append("id=").append(id);
        sb.append(", amount=").append(amount);
        sb.append(", timeWindow=").append(timeWindow);
        sb.append('}');
        return sb.toString();
    }
}
