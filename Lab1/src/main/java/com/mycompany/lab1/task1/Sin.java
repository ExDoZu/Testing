package com.mycompany.lab1.task1;

public class Sin {
    public static double sin(double x) {
        double ans = x;
        double tmp = x;
        double i = 1;
        while (Math.abs(tmp) > 0.001) {
            tmp = -tmp * x * x / ((2 * i) * (2 * i + 1));
            ans += tmp;
            i++;
        }
        return ans;
    }

    public static void main(String[] args) {
        
    }
}
