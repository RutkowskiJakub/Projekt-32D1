package com.example.planertreningow.treningi.encje;

import java.io.Serializable;
import java.util.ArrayList;

public class Exercise implements Serializable {
    private Integer _id;
    private String name;
    private ArrayList<Set> sets;

//    constructors
    public Exercise(){}
    public Exercise(String name){
        this.name = name;
    }
    public Exercise(String name, ArrayList<Set> series) {
        super();
        this.name = name;
        this.sets = series;
    }
    public Exercise(Integer _id, String name, ArrayList<Set> series){
        this._id = _id;
        this.name = name;
        this.sets = series;
    }

//    getters
    public String getName() {
        return name;
    }
    public Integer get_id() {
        return _id;
    }
    public ArrayList<Set> getSets() {
        return sets;
    }

//    setters
    public void setName(String name) {
        this.name = name;
    }
    public void set_id(Integer _id) {
        this._id = _id;
    }
    public void setSets(ArrayList<Set> sets) {
        this.sets = sets;
    }

//    utilities
    public void addSet(Set set){
        this.sets.add(set);
    }
    public void removeSet(Set set){
        this.sets.remove(set.get_id());
    }
}
