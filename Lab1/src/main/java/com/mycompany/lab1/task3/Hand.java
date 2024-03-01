package com.mycompany.lab1.task3;

public class Hand {
    private final boolean isHairy;
    private final Finger[] fingers;

    public Hand(boolean isHairy, Finger[] fingers) {
        this.isHairy = isHairy;
        this.fingers = fingers;
    }

    public boolean isHairy() {
        return isHairy;
    }

    public boolean checkFingersCleanliness() {
        if (fingers == null || fingers.length == 0){
            throw new IllegalStateException("No fingers to check cleanliness.");
        }
        for (Finger finger : fingers) {
            if (finger.isHasInk()) {
                return false;
            }
        }
        return true;
    }

    public boolean dirtyUp() {
        if (fingers == null || fingers.length == 0){
            throw new IllegalStateException("No fingers to dirty up");
        }
        boolean cleanFound = false;
        for (Finger finger : fingers) {
            if (!finger.isHasInk()) {
                finger.setInk(true);
                cleanFound = true;
                break;
            }
        }
        return cleanFound;
    }
}