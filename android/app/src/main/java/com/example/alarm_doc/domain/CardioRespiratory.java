package com.example.alarm_doc.domain;

import static java.lang.Math.abs;

public class CardioRespiratory {
    //TODO: replace using BaseVAlues Handler value
    //idea - method called getBaseValues that uses machine learning to get the best "normal" base values
    private int BASEBPM = -1;
    private int BASEFREQ = -1;
    private int BASEBREATH = -1;

    private int score;
    private int bpm;
    private int frequency;
    private int spikes;
    private int breathRate;
    //TODO: variables may be modificated in order to be more useful for the diagnosis

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

    public int getFrequency() {
        return frequency;
    }

    public int getSpikes() {
        return spikes;
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

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public void setSpikes(int spikes) {
        this.spikes = spikes;
    }

    public void setBreathRate(int breathRate) {
        this.breathRate = breathRate;
    }

    public void calculateScore(){
        int score = 100 - (1/4) * (abs(bpm-BASEBPM)/BASEBPM) - (1/4) * (abs(frequency-BASEFREQ)/BASEFREQ) -
                (1/4) * (spikes /10) - (1/4) * (abs(breathRate-BASEBREATH)/BASEBREATH);
        this.setScore(score);
        //TODO: max score como variavel global (?)
        //TODO: formula
    }
}
