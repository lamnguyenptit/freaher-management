package com.vmo.freshermanagement.model;

import javax.persistence.*;

@Entity
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double point;

    @ManyToOne
    @JoinColumn(name = "fresher_id")
    private Fresher fresher;

    @ManyToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exercise;

    public Result() {
    }

    public int getId() {
        return id;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public Fresher getFresher() {
        return fresher;
    }

    public void setFresher(Fresher fresher) {
        this.fresher = fresher;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }
}
