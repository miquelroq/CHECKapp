package com.example.alarm_doc.domain;

import static java.lang.Math.abs;

public class CardioRespiratory {

    private Profile profile;
    private int maxScore = 100;
    private int score;

    private int bpm;
    private int diff;
    private int breathRate;


    public CardioRespiratory(Profile profile, int bpm, int diff) {
        this.score = maxScore;
        this.profile = profile;
        this.bpm = bpm;
        this.diff = diff;
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
        int baseBPM = profile.getBaseValues().getCardioBpm();
        int baseBreath = profile.getBaseValues().getCardioBreath();

        int score = (int) (this.score - (1.0/2.0) * (abs(bpm-baseBPM)/(double) baseBPM) * 100 -
                       - (1.0/2.0) * (abs(breathRate-baseBreath)/(double) baseBreath) * 100);
        this.setScore(score);
    }
}
