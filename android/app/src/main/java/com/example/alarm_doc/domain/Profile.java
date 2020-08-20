package com.example.alarm_doc.domain;


import android.net.Uri;

import com.example.alarm_doc.domain.Conditions;
import com.example.alarm_doc.domain.Register;

import java.util.List;

public class Profile {

    private String name;
    private boolean female;
    private int     age;
    private float weight;
    private float height;
    private Uri photo;
    private List<Conditions> conditions;
    private List<Register> registers;

    public Profile() {
    }

    public Profile(String name, boolean female, int age, float weight, float height, Uri photo, List<Conditions> conditions){
        setName(name);
        setFemale(female);
        setAge(age);
        setWeight(weight);
        setHeight(height);
        setPhoto(photo);
        setConditions(conditions);
    }

    public String getName() {
        return name;
    }

    public boolean isFemale() {
        return female;
    }

    public int getAge() {
        return age;
    }

    public float getHeight() {
        return height;
    }

    public float getWeight() {
        return weight;
    }

    public Uri getPhoto() {
        return photo;
    }

    public List<Conditions> getConditions() {
        return conditions;
    }

    public List<Register> getRegisters() {
        return registers;
    }

    public void setName( String name) {
        this.name = name;
    }

    public void setFemale(boolean female) {
        this.female = female;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setPhoto(Uri photo) {
        this.photo = photo;
    }

    public void setConditions(List<Conditions> conditions) {
        this.conditions = conditions;
    }

    public void addRegister(Register register) {
        this.registers.add(register);
    }

}
