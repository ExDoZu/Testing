package com.mycompany.lab2.trig;

import com.mycompany.lab2.CsvHandler;

public class Cos extends TrigBasic {
    final Sin sin;

    public Cos(Sin sin) {
        this.sin = sin;
    }

    public double calc(double x) {
        double s = sin.calc(x);
        x = scaleInput(x);
        if (s > 1 || s < -1) {
            s = 1;
        }
        double cos = Math.sqrt(1 - s * s);
        if ((x > Math.PI / 2 && x < Math.PI * 3 / 2) ||
                x < -Math.PI / 2 && x > -Math.PI * 3 / 2) {
            cos = -cos;
        }
        CsvHandler.writeToCsv("cos", x, cos);
        return cos;
    }

}
