package com.gavynholt.crud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name="post_order")
public class PostOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_order_id")
    private int id;

    @Column(name="name")
    private String name;

    @JsonManagedReference
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="date_range_id")
    private DateRange dateRange;

    @JsonBackReference
    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="location_id")
    private Location location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public void setDateRange(DateRange dateRange) {
        this.dateRange = dateRange;
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
        sb.append(", location=").append(location);
        sb.append('}');
        return sb.toString();
    }
}
