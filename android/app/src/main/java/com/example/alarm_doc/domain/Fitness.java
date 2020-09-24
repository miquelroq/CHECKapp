package com.example.alarm_doc.domain;

import static java.lang.Math.abs;

public class Fitness {

    private int maxScore = 100;
    private int score;
    private Profile profile;

    private int bmi;
    //private int other_fitness_indicator_such_as_body_fat
    //TODO: add a height and weight confirmation screen that makes it possible to update
    // before each test so that bmi is accurate


    public Fitness(Profile profile) {
        this.score = maxScore;
        this.profile = profile;
    }

    public int getScore() {
        return score;
    }

    public int getBmi() {
        return bmi;
    }

    private void setScore(int score) {
        this.score = score;
    }

    public void setBmi(int bmi) {
        this.bmi = bmi;
    }

    public void calculateScore() {
        int baseBMI = profile.getBaseValues().getFitnessBmi();
        int score = (abs(bmi-baseBMI)/baseBMI) * 100 ;
        this.setScore(score);
    }

}
