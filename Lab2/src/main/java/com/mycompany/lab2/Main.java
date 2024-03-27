package com.mycompany.lab2;

import com.mycompany.lab2.log.Ln;
import com.mycompany.lab2.log.Log;
import com.mycompany.lab2.trig.Cos;
import com.mycompany.lab2.trig.Cot;
import com.mycompany.lab2.trig.Sin;

public class Main {
    public static void main(String[] args) {
        CsvHandler.openFile("results.csv");

        Sin sin = new Sin(1e-10);
        Cos cos = new Cos(sin);

        Cot cot = new Cot(sin, cos);
        Ln ln = new Ln(1e-6);
        Log log5 = new Log(5, ln);
        Log log10 = new Log(10, ln);

        // EqSystem eqSystem = new EqSystem(ln, log5, log10, cot);
        // System.out.println(eqSystem.calc(5));

        System.out.println(Math.cos(-1000000.0));
        System.out.println(cos.calc(-1000000.0));
        System.out.println(Math.sin(-1000000.0));
        System.out.println(sin.calc(-1000000.0));

        CsvHandler.closeAndSave();
    }

}
