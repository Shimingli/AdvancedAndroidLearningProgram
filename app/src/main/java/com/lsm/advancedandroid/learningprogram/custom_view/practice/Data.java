package com.lsm.advancedandroid.learningprogram.custom_view.practice;

/**
 * @author : ShiMing
 * @editor :
 * @description :
 * @created : 2020-04-22 09:01
 */
public class Data {
    private String name;
    private float number;
    private int color;

    public Data(String name, float number, int color) {
        this.name = name;
        this.number = number;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getNumber() {
        return number;
    }

    public void setNumber(float number) {
        this.number = number;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
