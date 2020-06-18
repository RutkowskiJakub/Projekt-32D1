package com.example.planertreningow.ustawienia;

import android.media.Image;

import java.io.Serializable;

public class User implements Serializable {
    String user_name;
    String weight;
    String goal_weight;
    String strength;
    String goal_strength;
    Image avatar;

    public User(String user_name, String weight, String goal_weight, String strength, String goal_strength) {
        this.user_name = user_name;
        this.weight = weight;
        this.goal_weight = goal_weight;
        this.strength = strength;
        this.goal_strength = goal_strength;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getGoal_weight() {
        return goal_weight;
    }

    public void setGoal_weight(String goal_weight) {
        this.goal_weight = goal_weight;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getGoal_strength() {
        return goal_strength;
    }

    public void setGoal_strength(String goal_strength) {
        this.goal_strength = goal_strength;
    }

    public Image getAvatar() {
        return avatar;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }
}
