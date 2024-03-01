package com.mycompany.lab1.task3;

public class Door {
    private final boolean isProperlyAligned;
    private boolean isLocked;

    public Door(boolean isProperlyAligned) {
        this.isProperlyAligned = isProperlyAligned;
    }

    public boolean isProperlyAligned() {
        return isProperlyAligned;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void tryLock() {
        if (isProperlyAligned) {
            isLocked = true;
        }
    }
}
