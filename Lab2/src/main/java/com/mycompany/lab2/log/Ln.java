package com.mycompany.lab2.log;

import com.mycompany.lab2.CsvHandler;

public class Ln {
    private final double precision;

    public Ln(double precision) {
        this.precision = precision;
    }

    public double calc(double x) {
        double result;
        if (x <= 0) {
            result = Double.NaN;
        } else {
            result = 0;
            double term = (x - 1) / (x + 1);
            double currentTerm = term;
            double lastTerm = term + 10 * precision;
            int n = 1;
            while (Math.abs(currentTerm - lastTerm) > precision) {
                result += currentTerm;
                lastTerm = currentTerm;
                currentTerm *= term * term * (2 * n - 1) / (2 * n + 1);
                n++;
            }
            result *= 2;
        }

        CsvHandler.writeToCsv("ln", x, result);

        return result;
    }
}
