package com.example.alarm_doc.domain;

import com.example.alarm_doc.domain.waves.Alpha;
import com.example.alarm_doc.domain.waves.Beta;
import com.example.alarm_doc.domain.waves.Delta;
import com.example.alarm_doc.domain.waves.Gamma;
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
    public Gamma gamma;
    public Neurologic(){
        score = maxScore;
    }

    public Neurologic(Profile profile, int alpha, int beta, int delta, int theta, int gamma) {
        this.alpha = new Alpha(0, alpha, profile);
        this.beta = new Beta(0, beta, profile);
        this.delta = new Delta(0, delta, profile);
        this.theta = new Theta(0, theta, profile);
        this.gamma = new Gamma(0, gamma, profile);
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

    public Gamma getGamma() {
        return gamma;
    }

    public void calculateScore() {
        int score = (int) (this.maxScore - 0.25 * alpha.getScore() - 0.25 * beta.getScore() - 0.25 * delta.getScore() - 0.25 * theta.getScore());
        this.setScore(score);
    }

}
