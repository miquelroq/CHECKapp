package com.example.alarm_doc.domain.waves;

import static java.lang.Math.abs;

public class Theta {
    //TODO: Turn those into global variables and use correct values
    //idea - method called getBaseValues that uses machine learning to get the best "normal" base values
    private int BASEFREQ = -1;

    private int score;
    private int pikes;
    private int frequency;

    public Theta(){

    }

    public Theta(int pikes, int frequency) {
        setPikes(pikes);
        setFrequency(frequency);
    }

    public int getScore() {
        return score;
    }

    public int getPikes() {
        return pikes;
    }

    public int getFrequency() {
        return frequency;
    }

    private void setScore(int score) {
        this.score = score;
    }

    public void setPikes(int pikes) {
        this.pikes = pikes;
        calculateScore();
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
        calculateScore();
    }

    public void calculateScore() {
        int score = 100 - (1/2) * (pikes/10) - (1/2) * (abs(frequency-BASEFREQ)/BASEFREQ);
        this.setScore(score);
    }
}
