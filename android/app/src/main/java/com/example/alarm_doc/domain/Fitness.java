package com.example.alarm_doc.domain;

import static java.lang.Math.abs;

public class Fitness {

    public int bmiIdeal; //TODO: replace using BaseVAlues Handler value
    public int bodyFatIdeal; //TODO: replace using BaseVAlues Handler value

    private int score;
    private int bmi;
    //TODO: add a height and weight confirmation screen that makes it possible to update before each test so that bmi is accurate

    //private int bodyFat;

    public Fitness() {
        //TODO: review
        this.score = 100;
    }

    public int getScore() {
        return score;
    }

    public int getBmi() {
        return bmi;
    }

    //public int getBodyFat() { return bodyFat; }

    private void setScore(int score) {
        this.score=score;
    }

    public void setBmi(int bmi) {
        this.bmi = bmi;
    }

    //public void setBodyFat(int bodyFat) { this.bodyFat = bodyFat; }

    public void calculateScore() {
        int score = 100 - (abs(bmi-bmiIdeal)/bmiIdeal) ;
        this.setScore(score);
        //TODO: max score como variavel global
        //TODO: formula
    }

}
