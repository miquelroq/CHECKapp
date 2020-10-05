package com.example.alarm_doc.domain.waves;

import com.example.alarm_doc.domain.Profile;

import static java.lang.Math.abs;

public class Beta {
    //idea - method called getBaseValues that uses machine learning to get the best "normal" base values

    private int score;
    private int maxScore = 100;
    private Profile profile;

    private int pikes;
    private int amp;

    public Beta(int pikes, int amp, Profile profile) {
        this.pikes = pikes;
        this.amp = amp;
        this.profile = profile;
    }

    public int getScore() {
        return score;
    }

    public int getPikes() {
        return pikes;
    }

    public int getAmp() {
        return amp;
    }

    private void setScore(int score) {
        this.score = score;
    }

    public void setPikes(int pikes) {
        this.pikes = pikes;
        calculateScore();
    }

    public void setAmp(int amp) {
        this.amp = amp;
        calculateScore();
    }

    public void calculateScore() {
        int baseAmp = profile.getBaseValues().getBetaAmp();
        int score = (int) (100 - 0.5 * (pikes * 10) - 0.5 * (abs(amp - baseAmp )/baseAmp) * 100);
        this.setScore(score);
    }
}
