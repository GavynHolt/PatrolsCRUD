package com.gavynholt.crud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name="patrol_check")
public class PatrolCheck {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="patrol_check_id")
    private int id;

    @Column(name="amount")
    private int amount;

//    @JsonManagedReference
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="time_window_id")
    private TimeWindow timeWindow;

    @Column(name = "notes")
    private String notes;

    @JsonBackReference
    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="post_order_id")
    private PostOrder postOrder;

    public PatrolCheck() {
    }

    public PatrolCheck(int amount, TimeWindow timeWindow) {
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

    public TimeWindow getTimeWindow() {
        return timeWindow;
    }

    public void setTimeWindow(TimeWindow timeWindow) {
        this.timeWindow = timeWindow;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public PostOrder getPostOrder() {
        return postOrder;
    }

    public void setPostOrder(PostOrder postOrder) {
        this.postOrder = postOrder;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Patrol{");
        sb.append("id=").append(id);
        sb.append(", amount=").append(amount);
        sb.append(", timeWindow=").append(timeWindow);
        sb.append(", notes=").append(notes);
        sb.append(", postOrder=").append(postOrder);
        sb.append('}');
        return sb.toString();
    }
}
