package com.example.alarm_doc.domain;

import java.util.List;

public class BaseValuesHandler {
    //this class gives the BASE values of all health parameters given the person stats
    //in the future, this class will fetch from the server the most updated BASE aka "normal" values

    private int cardioBpm;
    private int cardioFreq;
    private int cardioBreath;

    private int fitnessBmi;
    private int fitnessFat;

    private int nervesMdf;
    private int nervesMnf;
    private int nervesRms;
    private int nervesRrms;

    private int alphaFreq;
    private int betaFreq;
    private int deltaFreq;
    private int thetaFreq;

    public BaseValuesHandler(int age, boolean female, float weight, float height, int activityLevel, List<Conditions> conditionsList){
        calculateIdeal(age, female, weight, height, activityLevel, conditionsList);
    }

    public int getCardioBpm() {
        return cardioBpm;
    }

    public int getCardioFreq() {
        return cardioFreq;
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

    public int getNervesMdf() {
        return nervesMdf;
    }

    public int getNervesMnf() {
        return nervesMnf;
    }

    public int getNervesRms() {
        return nervesRms;
    }

    public int getNervesRrms() {
        return nervesRrms;
    }

    public int getAlphaFreq() {
        return alphaFreq;
    }

    public int getBetaFreq() {
        return betaFreq;
    }

    public int getDeltaFreq() {
        return deltaFreq;
    }

    public int getThetaFreq() {
        return thetaFreq;
    }

    private void setCardioBpm(int cardioBpm) {
        this.cardioBpm = cardioBpm;
    }

    private void setCardioFreq(int cardioFreq) {
        this.cardioFreq = cardioFreq;
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

    private void setNervesMdf(int nervesMdf) {
        this.nervesMdf = nervesMdf;
    }

    private void setNervesMnf(int nervesMnf) {
        this.nervesMnf = nervesMnf;
    }

    private void setNervesRms(int nervesRms) {
        this.nervesRms = nervesRms;
    }

    private void setNervesRrms(int nervesRrms) {
        this.nervesRrms = nervesRrms;
    }

    private void setAlphaFreq(int alphaFreq) {
        this.alphaFreq = alphaFreq;
    }

    private void setBetaFreq(int betaFreq) {
        this.betaFreq = betaFreq;
    }

    private void setDeltaFreq(int deltaFreq) {
        this.deltaFreq = deltaFreq;
    }

    private void setThetaFreq(int thetaFreq) {
        this.thetaFreq = thetaFreq;
    }

    public void calculateIdeal(int age, boolean female, float weight, float height, int activityLevel, List<Conditions> conditionsList) {
        //TODO: different values considering previous conditions

        //TODO: see what values are needed for each calculations and pass only them to the calculate functions
        calculateCardioBpm(age, female, weight, height, activityLevel);
        calculateCardioFreq(age, female, weight, height, activityLevel);
        calculateCardioBreath(age, female, weight, height, activityLevel);

        calculateFitnessBmi(age, female, weight, height, activityLevel);
        calculateFitnessFat(age, female, weight, height, activityLevel);

        calculateNervesMdf(age, female, weight, height, activityLevel);
        calculateNervesMnf(age, female, weight, height, activityLevel);
        calculateNervesRms(age, female, weight, height, activityLevel);
        calculateNervesRrms(age, female, weight, height, activityLevel);

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
        setCardioFreq(value);
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

    private void calculateNervesMdf(int age, boolean female, float weight, float height, int activityLevel) {
        int value = -1;
        //TODO calculate ideal value
        setNervesMdf(value);
    }

    private void calculateNervesMnf(int age, boolean female, float weight, float height, int activityLevel) {
        int value = -1;
        //TODO calculate ideal value
        setNervesMnf(value);
    }

    private void calculateNervesRms(int age, boolean female, float weight, float height, int activityLevel) {
        int value = -1;
        //TODO calculate ideal value
        setNervesRms(value);
    }

    private void calculateNervesRrms(int age, boolean female, float weight, float height, int activityLevel) {
        int value = -1;
        //TODO calculate ideal value
        setNervesRrms(value);
    }

    private void calculateAlphaFreq(int age, boolean female, float weight, float height, int activityLevel) {
        int value = -1;
        //TODO calculate ideal value
        setAlphaFreq(value);
    }

    private void calculateBetaFreq(int age, boolean female, float weight, float height, int activityLevel) {
        int value = -1;
        //TODO calculate ideal value
        setBetaFreq(value);
    }

    private void calculateDeltaFreq(int age, boolean female, float weight, float height, int activityLevel) {
        int value = -1;
        //TODO calculate ideal value
        setDeltaFreq(value);
    }

    private void calculateThetaFreq(int age, boolean female, float weight, float height, int activityLevel) {
        int value = -1;
        //TODO calculate ideal value
        setThetaFreq(value);
    }



}
