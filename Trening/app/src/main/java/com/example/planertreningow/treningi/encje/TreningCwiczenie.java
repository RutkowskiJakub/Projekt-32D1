package com.example.planertreningow.treningi.encje;

import java.io.Serializable;
import java.util.ArrayList;

public class TreningCwiczenie implements Serializable {
    private Integer trening_id;
    private Integer cwiczenie_id;

    public TreningCwiczenie(){}

    public TreningCwiczenie(Integer trening_id, Integer cwiczenie_id) {
        this.trening_id = trening_id;
        this.cwiczenie_id = cwiczenie_id;
    }

    public Integer getTrening_id() {
        return trening_id;
    }

    public void setTrening_id(Integer trening_id) {
        this.trening_id = trening_id;
    }

    public Integer getCwiczenie_id() {
        return cwiczenie_id;
    }

    public void setCwiczenie_id(Integer cwiczenie_id) {
        this.cwiczenie_id = cwiczenie_id;
    }
}
