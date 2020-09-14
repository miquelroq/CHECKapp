package com.example.alarm_doc.domain;

import static java.lang.Math.abs;

public class NervousMuscular {
    //TODO: replace using BaseVAlues Handler value
    //idea - method called getBaseValues that uses machine learning to get the best "normal" base values
    private int BASEREFLEXES = -1;

    private int CONSTFATIGUE = 20;


    private int score;
    private int reflexes;
    private int fatigue;

    public NervousMuscular() {
        //TODO: review and complete
        score = 100;

    }

    public int getScore() {
        return score;
    }

    public int getReflexes() {
        return reflexes;
    }

    public int getFatigue() {
        return fatigue;
    }

    private void setScore(int score) {
        this.score = score;
    }

    public void setReflexes(int reflexes) {
        this.reflexes = reflexes;
    }

    public void setFatigue(int fatigue) {
        this.fatigue = fatigue;
    }

    public void calculateScore(){
        int score = 100 - (1/2) * (abs(reflexes - BASEREFLEXES)/BASEREFLEXES) - (1/2) * (fatigue * CONSTFATIGUE);
        this.setScore(score);
        //TODO: max score como variavel global (?)
        //TODO: formula
    }
}
