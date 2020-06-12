package com.example.planertreningow.history;

import com.example.planertreningow.treningi.encje.Training;

import java.io.Serializable;
import java.util.ArrayList;


public class Event implements Serializable {
    private String date;
    private Training training;

    public Event(String date) {
        this.date = date;
    }

    public Event(String date, Training training) {
        this.date = date;
        this.training = training;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }
}
