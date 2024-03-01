package com.mycompany.lab1.task3;

public class Voice {
    private boolean isThin;

    public Voice(boolean isThin) {
        this.isThin = isThin;
    }

    public String scream(boolean isCrazy) {
        if (isThin) {
            return String.format("The%s voice screams%s.", isThin ? " thin" : "", isCrazy ? " insanely" : "");
        } else {
            return String.format("The%s voice screams%s.", isThin ? " thin" : "", isCrazy ? " insanely" : "");
        }
    }
}
