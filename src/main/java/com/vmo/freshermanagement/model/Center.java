package com.vmo.freshermanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Center {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "center")
    @JsonIgnore
    private Collection<Fresher> freshers;

    public Center() {
    }

    public Center(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Fresher> getFreshers() {
        return freshers;
    }

    public void setFreshers(Collection<Fresher> freshers) {
        this.freshers = freshers;
    }
}
