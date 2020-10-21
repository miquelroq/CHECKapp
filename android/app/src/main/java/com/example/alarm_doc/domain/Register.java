package com.example.alarm_doc.domain;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Register {

    //emotional score class
    private Emotional emotional;

    //nervous and muscular score class
    private NervousMuscular nervousMuscular;

    //neurologic score class
    private Neurologic neurologic;

    //fitness score class
    private Fitness fitness;

    //cardio-respiratory score class
    private CardioRespiratory cardioRespiratory;

    // Overall evaluation
    private boolean healthy;

    // Date in which the Register was generated
    private String date;


    public Register() {

    }

    @SuppressLint("SimpleDateFormat")
    public Register(Emotional emotional, NervousMuscular nervousMuscular, Neurologic neurologic, Fitness fitness, CardioRespiratory cardioRespiratory) {
        this.setEmotional(emotional);
        this.setNervousMuscular(nervousMuscular);
        this.setNeurologic(neurologic);
        this.setFitness(fitness);
        this.setCardioRespiratory(cardioRespiratory);
        this.date = new SimpleDateFormat("yyyy-MM-dd HH.mm").format(new Date());
    }

    public Emotional getEmotional() {
        return emotional;
    }

    public NervousMuscular getNervousMuscular() {
        return nervousMuscular;
    }

    public Neurologic getNeurologic() {
        return neurologic;
    }

    public Fitness getFitness() {
        return fitness;
    }

    public CardioRespiratory getCardioRespiratory() {
        return cardioRespiratory;
    }

    public int getEmotionalScore() {
        return emotional.getScore();
    }

    public double getNervousMuscularScore() {
        return nervousMuscular.getScore();
    }

    public int getNeurologicScore() {
        return neurologic.getScore();
    }

    public double getFitnessScore() {
        return fitness.getScore();
    }

    public int getCardioRespiratoryScore() {
        return cardioRespiratory.getScore();
    }

    public void setEmotional(Emotional emotional) {
        this.emotional = emotional;
    }

    public void setNervousMuscular(NervousMuscular nervousMuscular) {
        this.nervousMuscular = nervousMuscular;
    }

    public void setNeurologic(Neurologic neurologic) {
        this.neurologic = neurologic;
    }

    public void setFitness(Fitness fitness) {
        this.fitness = fitness;
    }

    public void setCardioRespiratory(CardioRespiratory cardioRespiratory) {
        this.cardioRespiratory = cardioRespiratory;
    }

    public boolean isHealthy() {
        return healthy;
    }

    public void setHealthy(boolean healthy) {
        this.healthy = healthy;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void calculateScores() {
        this.emotional.calculateScore();
        this.cardioRespiratory.calculateScore();
        this.fitness.calculateScore();
        this.nervousMuscular.calculateScore();
        // this.neurologic.calculateScore();
    }
}