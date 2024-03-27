package com.mycompany.lab2.trig;

import com.mycompany.lab2.CsvHandler;

public class Cot {
    Sin sin;
    Cos cos;

    public Cot(Sin sin, Cos cos) {
        this.sin = sin;
        this.cos = cos;
    }

    public double calc(double x) {
        final double s = sin.calc(x);
        final double c = cos.calc(x);
        double result;
        if (s == 0.0) {
            result = Double.NaN;
        } else {
            result = c / s;
        }
        CsvHandler.writeToCsv("cot", x, result);
        return result;
    }
}
