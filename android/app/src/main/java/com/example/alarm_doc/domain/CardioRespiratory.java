package com.example.alarm_doc.domain;

import static java.lang.Math.abs;

public class CardioRespiratory {
    //TODO: replace using BaseVAlues Handler value
    //idea - method called getBaseValues that uses machine learning to get the best "normal" base values
    private int BASEBPM = -1;
    private int BASEDIFF = -1;
    private int BASEBREATH = -1;

    private int score;
    private int bpm;
    private int diff;
    private int breathRate;


    public CardioRespiratory() {
        //TODO: review and complete
        score = 100;
    }

    public int getScore() {
        return score;
    }

    public int getBpm() {
        return bpm;
    }

    public int getDiff() {
        return diff;
    }

    public int getBreathRate() {
        return breathRate;
    }

    private void setScore(int score) {
        this.score = score;
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
    }

    public void setDiff(int diff) {
        this.diff = diff;
    }

    public void setBreathRate(int breathRate) {
        this.breathRate = breathRate;
    }

    public void calculateScore(){
        int score = 100 - (1/3) * (abs(bpm-BASEBPM)/BASEBPM) - (1/3) * (abs(diff - BASEDIFF)/ BASEDIFF) -
               - (1/3) * (abs(breathRate-BASEBREATH)/BASEBREATH);
        this.setScore(score);
        //TODO: max score como variavel global (?)
        //TODO: formula
    }
}
