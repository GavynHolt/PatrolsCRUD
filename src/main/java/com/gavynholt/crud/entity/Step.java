package com.gavynholt.crud.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name="step")
public class Step {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "step_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "photo_required")
    private Boolean photoRequired;

    @Column(name = "escalation_contact")
    private String escalationContact;

    @JsonBackReference
    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="post_order_id")
    private PostOrder postOrder;
}
