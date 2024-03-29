package com.example.alarm_doc.domain;

import android.util.Log;

import java.util.List;

public class BaseValuesHandler {
    //this class gives the BASE values of all health parameters given the person stats
    //in the future, this class will fetch from the server the most updated BASE aka "normal" values

    private int cardioBpm;
    private int cardioDiff;
    private int cardioBreath;

    private double fitnessBmi;
    private int fitnessFat;

    private double nervesChills;
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

    public double getFitnessBmi() {
        return fitnessBmi;
    }

    public double getNervesChills() {
        return nervesChills;
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

    private void setFitnessBmi(double fitnessBmi) {
        this.fitnessBmi = fitnessBmi;
    }

    public void setFitnessFat(int fitnessFat) {
        this.fitnessFat = fitnessFat;
    }

    private void setNervesChills(double nervesChills) {
        this.nervesChills = nervesChills;
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
        calculateCardioBpm(age, activityLevel);
        calculateCardioDiff(age);
        calculateCardioBreath(age);

        calculateFitnessBmi(age, female, weight, height, activityLevel);

        calculateNervesChills(age, female, weight, height, activityLevel);
        calculateNervesFatigue(age, female, weight, height, activityLevel);

        calculateAlphaFreq(age, female, weight, height, activityLevel);
        calculateBetaFreq(age, female, weight, height, activityLevel);
        calculateDeltaFreq(age, female, weight, height, activityLevel);
        calculateThetaFreq(age, female, weight, height, activityLevel);
    }

    private void calculateCardioBpm(int age, int activityLevel) {
        int value = -1;
        value = activityLevel * (220 - age);
        setCardioBpm(value);
    }

    private void calculateCardioDiff(int age) {
        //TODO: branch diff into two different indicators(min and max)!!!!!
        int value = -1;
        int min = (int) (0.55 * (220 - age));
        int max = (int) (0.85 * (220 - age));
        value = max - min;
        setCardioDiff(value);
    }

    private void calculateCardioBreath(int age) {
        //TODO divide into two indicators, max and min breath rate
        int value = -1;
        if (age <= (6/52.177457)){
            value = 35; //30-40
        }
        else if (age <= 0.5){
            value = 33; // 25-40
        }
        else if (age <= 3){
            value = 25; // 20-30
        }
        else if (age <= 6){
            value = 22; // 18-25
        }
        else if (age <= 10){
            value = 20; // 17-23
        }
        else if (age < 65){
            value = 15; //12-18
        }
        else if (age < 80){
            value = 20; //12-28
        }
        else{
            value = 20; //10-30
        }
        //TODO calculate ideal value
        setCardioBreath(value);
    }

    private void calculateFitnessBmi(int age, boolean female, float weight, float height, int activityLevel) {
        double value = -1;
        double idealWeight;

        double heightInFeet = height * 0.01 * 3.2808399;

        if (female){
            idealWeight = 45.5 + 2.3 * (max((heightInFeet - 5), 0) * 12);
        }
        else{
            idealWeight = 50 + 2.3 * (max((heightInFeet - 5), 0) * 12);
        }

        Log.d("FITNESS", ""+idealWeight);
        Log.d("FITNESS", ""+height);

        value = idealWeight/(height*height*0.01*0.01);
        setFitnessBmi(value);
    }

    private void calculateNervesChills(int age, boolean female, float weight, float height, int activityLevel) {
        double value = -1;
        value = 1;
        setNervesChills(value);
    }

    private void calculateNervesFatigue(int age, boolean female, float weight, float height, int activityLevel) {
        int value = -1;
        value = 1;
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

    private double max(double elem1, double elem2){
        if(elem1 > elem2) {
            return elem1;
        }
        return elem2;
    }



}
