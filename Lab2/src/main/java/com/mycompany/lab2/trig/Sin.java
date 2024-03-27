package com.mycompany.lab2.trig;

import com.mycompany.lab2.CsvHandler;

public class Sin extends TrigBasic {
    private final double precision;

    public Sin(double precision) {
        this.precision = precision;
    }

    public double calc(double x) {
        x = scaleInput(x);

        double result = 0;
        double term = x;
        double lastTerm = term + 10 * precision;
        int n = 1;
        while (Math.abs(term - lastTerm) > precision) {
            result += term;
            lastTerm = term;
            term *= -(x * x) / ((2 * n) * (2 * n + 1));
            n++;
        }
        CsvHandler.writeToCsv("sin", x, result);
        return result;
    }

}
