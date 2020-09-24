package com.example.alarm_doc.domain;

import java.util.List;

public class BaseValuesHandler {
    //this class gives the BASE values of all health parameters given the person stats
    //in the future, this class will fetch from the server the most updated BASE aka "normal" values

    private int cardioBpm;
    private int cardioDiff;
    private int cardioBreath;

    private int fitnessBmi;
    private int fitnessFat;

    private int nervesReflexes;
    private int nervesFatigue;

    private int alphaAmp;
    private int betaAmp;
    private int deltaAmp;
    private int thetaAmp;

    public BaseValuesHandler(int age, boolean female, float weight, float height, int activityLevel, List<Conditions> conditionsList){
        calculateIdeal(age, female, weight, height, activityLevel, conditionsList);
    }

    public int getCardioBpm() {
        return cardioBpm;
    }

    public int getCardioDiff() {
        return cardioDiff;
    }

    public int getCardioBreath() {
        return cardioBreath;
    }

    public int getFitnessBmi() {
        return fitnessBmi;
    }

    public int getFitnessFat() {
        return fitnessFat;
    }

    public int getNervesReflexes() {
        return nervesReflexes;
    }

    public int getNervesFatigue() {
        return nervesFatigue;
    }

    public int getAlphaAmp() {
        return alphaAmp;
    }

    public int getBetaAmp() {
        return betaAmp;
    }

    public int getDeltaAmp() {
        return deltaAmp;
    }

    public int getThetaAmp() {
        return thetaAmp;
    }

    private void setCardioBpm(int cardioBpm) {
        this.cardioBpm = cardioBpm;
    }

    private void setCardioDiff(int cardioDiff) {
        this.cardioDiff = cardioDiff;
    }

    private void setCardioBreath(int cardioBreath) {
        this.cardioBreath = cardioBreath;
    }

    private void setFitnessBmi(int fitnessBmi) {
        this.fitnessBmi = fitnessBmi;
    }

    public void setFitnessFat(int fitnessFat) {
        this.fitnessFat = fitnessFat;
    }

    private void setNervesReflexes(int nervesReflexes) {
        this.nervesReflexes = nervesReflexes;
    }

    private void setNervesFatigue(int nervesFatigue) {
        this.nervesFatigue = nervesFatigue;
    }

    private void setAlphaAmp(int alphaAmp) {
        this.alphaAmp = alphaAmp;
    }

    private void setBetaAmp(int betaAmp) {
        this.betaAmp = betaAmp;
    }

    private void setDeltaAmp(int deltaAmp) {
        this.deltaAmp = deltaAmp;
    }

    private void setThetaAmp(int thetaAmp) {
        this.thetaAmp = thetaAmp;
    }

    public void calculateIdeal(int age, boolean female, float weight, float height, int activityLevel, List<Conditions> conditionsList) {
        //TODO: different values considering previous conditions

        //TODO: see what values are needed for each calculations and pass only them to the calculate functions
        calculateCardioBpm(age, female, weight, height, activityLevel);
        calculateCardioFreq(age, female, weight, height, activityLevel);
        calculateCardioBreath(age, female, weight, height, activityLevel);

        calculateFitnessBmi(age, female, weight, height, activityLevel);
        calculateFitnessFat(age, female, weight, height, activityLevel);

        calculateNervesReflexes(age, female, weight, height, activityLevel);
        calculateNervesFatigue(age, female, weight, height, activityLevel);

        calculateAlphaFreq(age, female, weight, height, activityLevel);
        calculateBetaFreq(age, female, weight, height, activityLevel);
        calculateDeltaFreq(age, female, weight, height, activityLevel);
        calculateThetaFreq(age, female, weight, height, activityLevel);
    }

    private void calculateCardioBpm(int age, boolean female, float weight, float height, int activityLevel) {
        int value = -1;
        //TODO calculate ideal value
        setCardioBpm(value);
    }

    private void calculateCardioFreq(int age, boolean female, float weight, float height, int activityLevel) {
        int value = -1;
        //TODO calculate ideal value
        setCardioDiff(value);
    }

    private void calculateCardioBreath(int age, boolean female, float weight, float height, int activityLevel) {
        int value = -1;
        //TODO calculate ideal value
        setCardioBreath(value);
    }

    private void calculateFitnessBmi(int age, boolean female, float weight, float height, int activityLevel) {
        int value = -1;
        //TODO calculate ideal value
        setFitnessBmi(value);
    }

    private void calculateFitnessFat(int age, boolean female, float weight, float height, int activityLevel) {
        int value = -1;
        //TODO calculate ideal value
        setFitnessFat(value);
    }

    private void calculateNervesReflexes(int age, boolean female, float weight, float height, int activityLevel) {
        int value = -1;
        //TODO calculate ideal value
        setNervesReflexes(value);
    }

    private void calculateNervesFatigue(int age, boolean female, float weight, float height, int activityLevel) {
        int value = -1;
        //TODO calculate ideal value
        setNervesFatigue(value);
    }


    private void calculateAlphaFreq(int age, boolean female, float weight, float height, int activityLevel) {
        int value = -1;
        //TODO calculate ideal value
        setAlphaAmp(value);
    }

    private void calculateBetaFreq(int age, boolean female, float weight, float height, int activityLevel) {
        int value = -1;
        //TODO calculate ideal value
        setBetaAmp(value);
    }

    private void calculateDeltaFreq(int age, boolean female, float weight, float height, int activityLevel) {
        int value = -1;
        //TODO calculate ideal value
        setDeltaAmp(value);
    }

    private void calculateThetaFreq(int age, boolean female, float weight, float height, int activityLevel) {
        int value = -1;
        //TODO calculate ideal value
        setThetaAmp(value);
    }



}
