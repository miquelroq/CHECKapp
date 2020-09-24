package com.example.alarm_doc.domain;

import static java.lang.Math.abs;

public class CardioRespiratory {

    private Profile profile;
    private int maxScore = 100;
    private int score;

    private int bpm;
    private int diff;
    private int breathRate;


    public CardioRespiratory(Profile profile) {
        this.score = maxScore;
        this.profile = profile;
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
        int baseDiff = profile.getBaseValues().getCardioDiff();
        int score = this.score - (1/3) * (abs(bpm-baseBPM)/baseBPM) * 100 - (1/3) * (abs(diff - baseDiff)/ baseDiff) * 100 -
               - (1/3) * (abs(breathRate-baseBreath)/baseBreath) * 100;
        this.setScore(score);
    }
}
