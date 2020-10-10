package com.example.alarm_doc.domain;

import static java.lang.Math.abs;

public class Fitness {

    private int maxScore = 100;
    private double score;
    private Profile profile;

    private double bmi;
    //private int other_fitness_indicator_such_as_body_fat
    //TODO: add a height and weight confirmation screen that makes it possible to update
    // before each test so that bmi is accurate


    public Fitness(Profile profile) {
        this.score = maxScore;
        this.profile = profile;
        this.bmi = calculateBMI();
    }

    private double calculateBMI() {
        return this.profile.getWeight() / ((this.profile.getHeight() * 0.01) * (this.profile.getHeight() * 0.01));
    }

    public double getScore() {
        return score;
    }

    public double getBmi() {
        return bmi;
    }

    private void setScore(double score) {
        this.score = score;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public void calculateScore() {
        double baseBMI = profile.getBaseValues().getFitnessBmi();
        double score = (abs(bmi-baseBMI)/baseBMI) * 100 ;
        this.setScore(score);
    }

}
