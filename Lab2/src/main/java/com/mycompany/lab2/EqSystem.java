package com.mycompany.lab2;

import com.mycompany.lab2.log.Ln;
import com.mycompany.lab2.log.Log;
import com.mycompany.lab2.trig.Cot;

public class EqSystem {
    final Ln ln;
    final Log log5;
    final Log log10;
    final Cot cot;

    EqSystem(Ln ln, Log log5, Log log10, Cot cot) {
        this.ln = ln;
        this.log5 = log5;
        this.log10 = log10;
        this.cot = cot;
    }

    public double calc(double x) {
        double result;
        if (x <= 0.0) {
            result = cot.calc(x);
        } else {
            double l10 = log10.calc(x);
            if (l10 == 0.0) {
                result = Double.NaN;
            } else {
                final double tmp = ((log5.calc(x) + ln.calc(x)) / l10);
                result = (((tmp * tmp) - l10) - log5.calc(x));
            }
        }
        CsvHandler.writeToCsv("eqSystem", x, result);
        return result;

    }

}
