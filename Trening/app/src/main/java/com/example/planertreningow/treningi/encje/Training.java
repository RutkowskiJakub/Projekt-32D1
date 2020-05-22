package com.example.planertreningow.treningi.encje;

import java.io.Serializable;
import java.util.ArrayList;

public class Training implements Serializable {
    private Integer _id;
    private String name;
    private ArrayList<Exercise> exercises;

//    constructors
    public Training(){}
    public Training(String name){
        this.name = name;
    }
    public Training(String name, ArrayList<Exercise>exercises) {
        super();
        this.name = name;
        this.exercises=exercises;
    }

//    getters
    public Integer get_id() {
        return _id;
    }
    public ArrayList<Exercise> getExercises() {
        return exercises;
    }
    public String getName() {
        return name;
    }

//    setters
    public void setName(String name) {
        this.name = name;
    }
    public void set_id(Integer _id) {
        this._id = _id;
    }
    public void setExercises(ArrayList<Exercise> exercises) {
        this.exercises = exercises;
    }

//    utilities
    public void addExercise(Exercise exercise){
        this.exercises.add(exercise);
    }
    public void removeExercise(Exercise exercise){
        this.exercises.remove(exercise.get_id());
    }
}
