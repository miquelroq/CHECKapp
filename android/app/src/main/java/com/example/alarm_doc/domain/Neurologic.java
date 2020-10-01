package com.example.alarm_doc.domain;

import com.example.alarm_doc.domain.waves.Alpha;
import com.example.alarm_doc.domain.waves.Beta;
import com.example.alarm_doc.domain.waves.Delta;
import com.example.alarm_doc.domain.waves.Theta;



public class Neurologic {
    //TODO: if alpha, beta, delta and theta waves come to be processed exactly the same consider
    // merging all the corresponding classes into a "Wave" class

    private int score;
    private int maxScore = 100;

    public Alpha alpha;
    public Beta beta;
    public Delta delta;
    public Theta theta;

    public Neurologic(){
        score = maxScore;
    }

    public int getScore() {
        return score;
    }

    public Alpha getAlpha() {
        return alpha;
    }

    public Beta getBeta() {
        return beta;
    }

    public Delta getDelta() {
        return delta;
    }

    public Theta getTheta() {
        return theta;
    }

    private void setScore(int score) {
        this.score = score;
    }

    public void setAlpha(Alpha alpha) {
        this.alpha = alpha;
    }

    public void setBeta(Beta beta) {
        this.beta = beta;
    }

    public void setDelta(Delta delta) {
        this.delta = delta;
    }

    public void setTheta(Theta theta) {
        this.theta = theta;
    }

    public void calculateScore() {
        int score = this.maxScore - (1/4) * alpha.getScore() - (1/4) * beta.getScore() - (1/4) * delta.getScore() - (1/4) * theta.getScore();
        this.setScore(score);
    }

}
