package com.mycompany.lab1.task3;

public class Person {
    private final String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void pushShoulderToDoor(Door door) {
        door.tryLock();
    }
}