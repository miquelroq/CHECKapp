package com.example.alarm_doc.domain;

import static java.lang.Math.abs;

public class NervousMuscular {
    //TODO: Turn those into global variables and use correct values
    //idea - method called getBaseValues that uses machine learning to get the best "normal" base values
    private int BASEMDF = -1;
    private int BASEMNF = -1;
    private int BASERMS = -1;
    private int BASERRMS = -1;


    private int score;
    private int mdf;
    private int mnf;
    private int rms;
    private int rrms;

    public NervousMuscular() {
        //TODO: review and complete
        score = 100;

    }

    public int getScore() {
        return score;
    }

    public int getMdf() {
        return mdf;
    }

    public int getMnf() {
        return mnf;
    }

    public int getRms() {
        return rms;
    }

    public int getRrms() {
        return rrms;
    }

    private void setScore(int score) {
        this.score = score;
    }

    public void setMdf(int mdf) {
        this.mdf = mdf;
    }

    public void setMnf(int mnf) {
        this.mnf = mnf;
    }

    public void setRms(int rms) {
        this.rms = rms;
    }

    public void setRrms(int rrms) {
        this.rrms = rrms;
    }

    public void calculateScore(){
        int score = 100 - (1/4) * (abs(mdf-BASEMDF)/BASEMDF) - (1/4) * (abs(mdf-BASEMNF)/BASEMNF) -
                (1/4) * (abs(mdf-BASERMS)/BASERMS) - (1/4) * (abs(mdf-BASERRMS)/BASERRMS);
        this.setScore(score);
        //TODO: max score como variavel global (?)
        //TODO: formula
    }
}
