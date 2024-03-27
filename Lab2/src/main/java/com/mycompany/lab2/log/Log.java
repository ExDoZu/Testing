package com.mycompany.lab2.log;

import com.mycompany.lab2.CsvHandler;

public class Log {
    private final Ln ln;
    private final int base;

    public Log(int base, Ln ln) {

        this.ln = ln;
        this.base = base;
    }

    public double calc(double x) {
        if (base <= 0 || x <= 0) {
            return Double.NaN;
        }
        double result = ln.calc(x) / ln.calc(base);
        CsvHandler.writeToCsv("log" + (int) base, x, result);
        return result;
    }
}
