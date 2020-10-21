package com.example.alarm_doc.domain;

import static java.lang.Math.abs;

public class NervousMuscular {
    //idea : method called getBaseValues that uses machine learning to get the best "normal" base values

    private int maxScore = 100;
    private double score;
    private Profile profile;

    private int chills;
    private int fatigue;

    public NervousMuscular(int chills, int fatigue, Profile profile) {
        this.chills = chills;
        this.fatigue = fatigue;
        this.profile = profile;
        this.score = maxScore;
    }

    public double getScore() {
        return score;
    }

    public int getChills() {
        return chills;
    }

    public int getFatigue() {
        return fatigue;
    }

    private void setScore(double score) {
        this.score = score;
    }

    public void setChills(int chills) {
        this.chills = chills;
    }

    public void setFatigue(int fatigue) {
        this.fatigue = fatigue;
    }

    public void calculateScore(){
        double baseReflexes = this.profile.getBaseValues().getNervesChills();
        int baseFatigue = this.profile.getBaseValues().getNervesFatigue();
        double score = this.score - (abs(chills - baseReflexes)) * 10 - (abs(fatigue - baseFatigue)) * 10;
        this.setScore(score);
    }
}
