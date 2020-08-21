package com.example.alarm_doc.domain;

public class Emotional {
    private int score;
    //TODO: replace following vars  by variables that describe what we're measuring in the questions
    private int var1;
    private int var2;

    public Emotional() {
        //TODO: review
        this.score = 100;
        this.var1 = 0;
        this.var2 = 0;
    }

    public int getScore() {
        return score;
    }

    public int getVar1() {
        return var1;
    }

    public int getVar2() {
        return var2;
    }

    private void setScore(int score) {
        this.score=score;
    }

    public void setVar1(int var1) {
        this.var1 = var1;
    }

    public void setVar2(int var2) {
        this.var2 = var2;
    }

    public int calculateScore() {
        int score = 100 - (1/2) * var1 - (1/2) * var2;
        this.setScore(score);
        return score;
        //TODO: max score como variavel global
        //TODO: formula
    }

}
