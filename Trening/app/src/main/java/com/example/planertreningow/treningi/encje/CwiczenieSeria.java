package com.example.planertreningow.treningi.encje;

import java.io.Serializable;
import java.util.ArrayList;

public class CwiczenieSeria implements Serializable {
    private Integer cwiczenie_id;
    private Integer repeats;
    private Double weights;

    public CwiczenieSeria(){}

    public CwiczenieSeria(Integer cwiczenie_id, Seria seria) {
        this.cwiczenie_id = cwiczenie_id;
        this.repeats=seria.getRepeats();
        this.weights=seria.getWeights();
    }

    public Integer getCwiczenie_id() {
        return cwiczenie_id;
    }

    public void setCwiczenie_id(Integer cwiczenie_id) {
        this.cwiczenie_id = cwiczenie_id;
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
}
