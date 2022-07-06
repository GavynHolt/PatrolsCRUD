package com.gavynholt.crud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name="date_range")
public class DateRange {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="date_range_id")
    private int id;

    @Column(name="start_date")
    private String start;

    @Column(name="end_date")
    private String end;
    @JsonBackReference
    @OneToOne(mappedBy="dateRange",
            cascade= {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private PostOrder postOrder;

    public DateRange() {

    }

    public DateRange(String start, String end) {
        this.start = start;
        this.end = end;
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

    public PostOrder getPostOrder() {
        return postOrder;
    }

    public void setPostOrder(PostOrder postOrder) {
        this.postOrder = postOrder;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DateRange{");
        sb.append("id=").append(id);
        sb.append(", start='").append(start).append('\'');
        sb.append(", end='").append(end).append('\'');
        sb.append(", postOrder=").append(postOrder);
        sb.append('}');
        return sb.toString();
    }
}
