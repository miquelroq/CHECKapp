package com.example.alarm_doc.domain;

import java.util.ArrayList;

public class RequestDto {
    //This class will have arrays of ints colected from the Bitalino during the checkup and it will be serialized in an API call so that we can obtain
    //int values that will be used in the health indexes calculation.

    //TODO: add Score classes

    private ArrayList<Integer> ecg;
    private ArrayList<Integer> eeg;
    private ArrayList<Integer> temp;

    public ArrayList<Integer> getEcg() {
        return ecg;
    }

    public ArrayList<Integer> getTemp() {
        return temp;
    }

    public ArrayList<Integer> getEeg() {
        return eeg;
    }

    public void setEcg(ArrayList<Integer> ecg) {
        this.ecg = ecg;
    }

    public void setEeg(ArrayList<Integer> eeg) {
        this.eeg = eeg;
    }

    public void setTemp(ArrayList<Integer> temp) {
        this.temp = temp;
    }
}
