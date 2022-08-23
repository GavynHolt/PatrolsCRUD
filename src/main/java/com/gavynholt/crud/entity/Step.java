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

    public Boolean getPhotoRequired() {
        return photoRequired;
    }

    public void setPhotoRequired(Boolean photoRequired) {
        this.photoRequired = photoRequired;
    }

    public String getEscalationContact() {
        return escalationContact;
    }

    public void setEscalationContact(String escalationContact) {
        this.escalationContact = escalationContact;
    }

    public PostOrder getPostOrder() {
        return postOrder;
    }

    public void setPostOrder(PostOrder postOrder) {
        this.postOrder = postOrder;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Step{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", photoRequired=").append(photoRequired);
        sb.append(", escalationContact='").append(escalationContact).append('\'');
        sb.append(", postOrder=").append(postOrder);
        sb.append('}');
        return sb.toString();
    }
}
