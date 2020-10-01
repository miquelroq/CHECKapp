package com.example.alarm_doc.domain;


import java.util.ArrayList;
import java.util.List;

public class Profile {

    private int id; //TODO: auto incremented sequential ID
    private String name;
    private boolean female;
    private int     age;
    private float weight;
    private float height;
    private String photo;
    private List<Conditions> conditions;
    private int activityLevel;
    private List<Register> registers = new ArrayList<>();
    private BaseValuesHandler baseValues;


    public Profile(String name, boolean female, int age, float weight, float height, int activityLevel, String photo, List<Conditions> conditions){
        this.name = name;
        this.female = female;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.photo = photo;
        this.conditions = conditions;
        baseValues = new BaseValuesHandler(age, female,  weight, height, activityLevel, conditions);
    }

    public int getId() {
        return id;
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

    public String getPhoto() {
        return photo;
    }

    public List<Conditions> getConditions() {
        return conditions;
    }

    public int getActivityLevel() {
        return activityLevel;
    }

    public List<Register> getRegisters() {
        return registers;
    }

    public BaseValuesHandler getBaseValues() {
        return baseValues;
    }

    public void setName( String name) {
        this.name = name;
    }

    public void setFemale(boolean female) {
        this.female = female;
        baseValues.calculateIdeal(age,female,  weight, height, activityLevel, conditions);
    }

    public void setAge(int age) {
        this.age = age;
        baseValues.calculateIdeal(age,female,  weight, height, activityLevel, conditions);
    }

    public void setWeight(float weight) {
        this.weight = weight;
        baseValues.calculateIdeal(age,female,  weight, height, activityLevel, conditions);
    }

    public void setHeight(float height) {
        this.height = height;
        baseValues.calculateIdeal(age,female,  weight, height, activityLevel, conditions);
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setConditions(List<Conditions> conditions) {
        this.conditions = conditions;
        baseValues.calculateIdeal(age,female,  weight, height, activityLevel, conditions);
    }

    public void setActivityLevel(int activityLevel) {
        this.activityLevel = activityLevel;
        baseValues.calculateIdeal(age,female,  weight, height, activityLevel, conditions);
    }

    public void setRegisters(List<Register> registers) {
        this.registers = registers;
    }

    public void setBaseValues(BaseValuesHandler baseValues) {
        this.baseValues = baseValues;
    }

    public void addRegister(Register register) {
        this.registers.add(register);
    }
}
