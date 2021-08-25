package com.vmo.freshermanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class ProgrammingLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @OneToMany(mappedBy = "programmingLanguage")
    @JsonIgnore
    private Collection<Fresher> freshers;

    public ProgrammingLanguage() {
    }

    public ProgrammingLanguage(String name) {
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
