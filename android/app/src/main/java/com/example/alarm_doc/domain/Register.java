package com.example.alarm_doc.domain;

public class Register {

    //0-100 value representing emotional score
    private int emotional;
    //nervous and muscular score class
    private NervousMuscular nervousMuscular;
    //neurologic score class
    private Neurologic neurologic;
    //fitness score class
    private Fitness fitness;
    //cardio-respiratory score class
    private CardioRespiratory cardioRespiratory;

    public int getEmotional() {
        return emotional;
    }

    public void setEmotional(int emotional) {
        this.emotional = emotional;
    }

    public NervousMuscular getNervousMuscular() {
        return nervousMuscular;
    }

    public void setNervousMuscular(NervousMuscular nervousMuscular) {
        this.nervousMuscular = nervousMuscular;
    }

    public Neurologic getNeurologic() {
        return neurologic;
    }

    public void setNeurologic(Neurologic neurologic) {
        this.neurologic = neurologic;
    }

    public Fitness getFitness() {
        return fitness;
    }

    public void setFitness(Fitness fitness) {
        this.fitness = fitness;
    }

    public CardioRespiratory getCardioRespiratory() {
        return cardioRespiratory;
    }

    public void setCardioRespiratory(CardioRespiratory cardioRespiratory) {
        this.cardioRespiratory = cardioRespiratory;
    }
}
