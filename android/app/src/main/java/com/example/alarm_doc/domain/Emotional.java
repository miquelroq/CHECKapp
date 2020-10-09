package com.example.alarm_doc.domain;

public class Emotional {
    private int score;
    //TODO: replace following vars  by variables that describe what we're measuring in the questions
    private int var1;
    private int var2;
    private int var3;
    private int var4;
    private int var5;
    private int var6;
    private int var7;
    private int var8;
    private int var9;
    private int var10;

    public Emotional() {
        //TODO: review
        this.score = 100;
        this.var1 = 0;
        this.var2 = 0;
        this.var3 = 0;
        this.var4 = 0;
        this.var5 = 0;
        this.var6 = 0;
        this.var7 = 0;
        this.var8 = 0;
        this.var9 = 0;
        this.var10 = 0;
    }

    public int getScore() {
        return score;
    }

    public int getVar1() {
        return var1;
    }

    public int getVar2() {
        return var2;
    }

    private void setScore(int score) {
        this.score=score;
    }

    public void setVar1(int var1) {
        this.var1 = var1;
    }

    public void setVar2(int var2) {
        this.var2 = var2;
    }

    public void calculateScore() {
        int score = 100 - (1/2) * var1 - (1/2) * var2;
        this.setScore(score);
        //TODO: max score como variavel global
        //TODO: formula
    }

    public int getVar3() {
        return var3;
    }

    public void setVar3(int var3) {
        this.var3 = var3;
    }

    public int getVar4() {
        return var4;
    }

    public void setVar4(int var4) {
        this.var4 = var4;
    }

    public int getVar5() {
        return var5;
    }

    public void setVar5(int var5) {
        this.var5 = var5;
    }

    public int getVar6() {
        return var6;
    }

    public void setVar6(int var6) {
        this.var6 = var6;
    }

    public int getVar7() {
        return var7;
    }

    public void setVar7(int var7) {
        this.var7 = var7;
    }

    public int getVar8() {
        return var8;
    }

    public void setVar8(int var8) {
        this.var8 = var8;
    }

    public int getVar9() {
        return var9;
    }

    public void setVar9(int var9) {
        this.var9 = var9;
    }

    public int getVar10() {
        return var10;
    }

    public void setVar10(int var10) {
        this.var10 = var10;
    }
}
