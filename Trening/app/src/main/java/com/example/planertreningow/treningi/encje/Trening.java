package com.example.planertreningow.treningi.encje;

import java.io.Serializable;
import java.util.ArrayList;

public class Trening implements Serializable {
    private Integer _id;
    private String name;

    public Trening(){}

    public Trening(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }
}