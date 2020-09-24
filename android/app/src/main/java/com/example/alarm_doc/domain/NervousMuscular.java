package com.example.alarm_doc.domain;

import static java.lang.Math.abs;

public class NervousMuscular {
    //idea : method called getBaseValues that uses machine learning to get the best "normal" base values

    private int maxScore = 100;
    private int score;
    private Profile profile;

    private int reflexes;
    private int fatigue;

    public NervousMuscular(Profile profile) {
        this.profile = profile;
        this.score = maxScore;
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
        int baseReflexes = this.profile.getBaseValues().getNervesReflexes();
        int baseFatigue = this.profile.getBaseValues().getNervesFatigue();
        int score = this.score - (1/2) * (abs(reflexes - baseReflexes)/baseReflexes) * 100 - (1/2) * (abs(fatigue - baseFatigue)/baseFatigue) * 100;
        this.setScore(score);
    }
}
