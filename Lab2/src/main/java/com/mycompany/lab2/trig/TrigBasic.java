package com.mycompany.lab2.trig;

public abstract class TrigBasic {

    protected double scaleInput(double x) {
        int times = (int) (x / (2 * Math.PI));
        x -= times * 2 * Math.PI;
        return x;
    }
}
