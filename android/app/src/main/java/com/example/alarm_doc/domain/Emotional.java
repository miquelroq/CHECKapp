package com.example.alarm_doc.domain;

public class Emotional {
    private int score;
    private int q1;
    private int q2;
    private int q3;
    private int q4;
    private int q5;
    private int q6;
    private int q7;
    private int q8;
    private int q9;
    private int q10;
    private int max_score = 100;
    private Profile p;

    public Emotional(int q1, int q2, int q3, int q4, int q5, int q6, int q7, int q8, int q9, int q10, Profile p) {
        this.score = max_score;
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.q4 = q4;
        this.q5 = q5;
        this.q6 = q6;
        this.q7 = q7;
        this.q8 = q8;
        this.q9 = q9;
        this.q10 = q10;
        this.p = p;
    }

    public int getScore() {
        return score;
    }

    public int getQ1() {
        return q1;
    }

    public int getQ2() {
        return q2;
    }

    private void setScore(int score) {
        this.score=score;
    }

    public void setQ1(int q1) {
        this.q1 = q1;
    }

    public void setQ2(int q2) {
        this.q2 = q2;
    }

    public void calculateScore() {
        int score = max_score + 10 - (1/5) * q1 - (1/5) * q2 - (1/5) * q3 - (1/5) * q4 - (1/5) * q5 - (1/5) * q6 - (1/5) * q7 - (1/5) * q8 - (1/5) * q9 - (1/5) * q10;
        this.setScore(score);
        //TODO: max score como variavel global
    }

    public int getQ3() {
        return q3;
    }

    public void setQ3(int q3) {
        this.q3 = q3;
    }

    public int getQ4() {
        return q4;
    }

    public void setQ4(int q4) {
        this.q4 = q4;
    }

    public int getQ5() {
        return q5;
    }

    public void setQ5(int q5) {
        this.q5 = q5;
    }

    public int getQ6() {
        return q6;
    }

    public void setQ6(int q6) {
        this.q6 = q6;
    }

    public int getQ7() {
        return q7;
    }

    public void setQ7(int q7) {
        this.q7 = q7;
    }

    public int getQ8() {
        return q8;
    }

    public void setQ8(int q8) {
        this.q8 = q8;
    }

    public int getQ9() {
        return q9;
    }

    public void setQ9(int q9) {
        this.q9 = q9;
    }

    public int getQ10() {
        return q10;
    }

    public void setQ10(int q10) {
        this.q10 = q10;
    }
}
