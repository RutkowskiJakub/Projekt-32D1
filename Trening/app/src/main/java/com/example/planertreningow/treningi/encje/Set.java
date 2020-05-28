package com.example.planertreningow.treningi.encje;

import java.io.Serializable;
import java.util.ArrayList;

public class Set implements Serializable {
    private Integer _id;
    private Integer repeats;
    private Double weights;

//    constructors
    public Set(){}
    public Set(Integer repeats, Double weights) {
        super();
        this.repeats = repeats;
        this.weights = weights;
    }

//    getters
    public Integer get_id() {
        return _id;
    }
    public Integer getRepeats() {
        return repeats;
    }
    public Double getWeights() {
        return weights;
    }

//    setters
    public void setWeights(Double weights) {
        this.weights = weights;
    }
    public void setRepeats(Integer repeats) {
        this.repeats = repeats;
    }
    public void set_id(Integer _id) {
        this._id = _id;
    }
}
