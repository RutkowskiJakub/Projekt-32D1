package com.example.planertreningow.treningi.encje;

import java.io.Serializable;
import java.util.ArrayList;

public class Seria implements Serializable {
    private Integer _id;
    private Integer repeats;
    private Double weights;

    public Seria(){}

    public Seria(Integer repeats, Double weights) {
        super();
        this.repeats = repeats;
        this.weights = weights;
    }

    public Integer getRepeats() {
        return repeats;
    }

    public void setRepeats(Integer repeats) {
        this.repeats = repeats;
    }

    public Double getWeights() {
        return weights;
    }

    public void setWeights(Double weights) {
        this.weights = weights;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }
}
