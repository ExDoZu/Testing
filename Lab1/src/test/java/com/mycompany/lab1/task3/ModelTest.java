package com.mycompany.lab1.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {
    private Door alignedDoor;
    private Door misalignedDoor;
    private Hand cleanHand;
    private Hand dirtyHand;
    private Hand hairyHand;
    private Voice normalVoice;
    private Voice thinVoice;

    @BeforeEach
    void setUp() {
        alignedDoor = new Door(true);
        misalignedDoor = new Door(false);

        Finger[] fingers = new Finger[5];
        for (int i = 0; i < 5; i++) {
            fingers[i] = new Finger(false);
        }

        cleanHand = new Hand(false, fingers);

        Finger[] dirtyFingers = new Finger[5];
        for (int i = 0; i < 4; i++) {
            dirtyFingers[i] = new Finger(false);
        }
        dirtyFingers[4] = new Finger(true);

        dirtyHand = new Hand(false, dirtyFingers);

        hairyHand = new Hand(true, fingers);
        normalVoice = new Voice(false);
        thinVoice = new Voice(true);
    }

    @Test
    void testTryLockWithProperlyAlignedDoor() {
        Person person = new Person("Arthur");
        person.pushShoulderToDoor(alignedDoor);
        assertTrue(alignedDoor.isLocked());
    }

    @Test
    void testTryLockWithMisalignedDoor() {
        Person person = new Person("Arthur");
        person.pushShoulderToDoor(misalignedDoor);
        assertFalse(misalignedDoor.isLocked());
    }

    @Test
    void testCheckCleanlinessWithCleanHand() {
        assertTrue(cleanHand.checkFingersCleanliness());
    }

    @Test
    void testCheckCleanlinessWithDirtyHand() {
        assertFalse(dirtyHand.checkFingersCleanliness());
    }

    @Test
    void testCheckHairyHand() {
        assertTrue(hairyHand.isHairy());
    }

    @Test
    void testScreamNormalVoice() {
        assertEquals("The voice screams.", normalVoice.scream(false));
    }

    @Test
    void testScreamCrazyVoice() {
        assertEquals("The voice screams insanely.", normalVoice.scream(true));
    }

    @Test
    void testThinScreamNormalVoice() {
        assertEquals("The thin voice screams.", thinVoice.scream(false));
    }

    @Test
    void testThinScreamCrazyVoice() {
        assertEquals("The thin voice screams insanely.", thinVoice.scream(true));
    }

    @Test
    void testSetInkOnFinger() {
        Finger finger = new Finger(false);
        assertFalse(finger.isHasInk());
        finger.setInk(true);
        assertTrue(finger.isHasInk());
    }

    @Test
    void testDirtyUp() {
        assertTrue(cleanHand.dirtyUp());
        assertFalse(cleanHand.checkFingersCleanliness());
        cleanHand.dirtyUp();
        cleanHand.dirtyUp();
        cleanHand.dirtyUp();
        cleanHand.dirtyUp();
        assertFalse(cleanHand.dirtyUp());
        assertTrue(dirtyHand.dirtyUp());
        assertFalse(dirtyHand.checkFingersCleanliness());
    }

    @Test
    void testDirtyUpWithNullFingers() {
        Hand hand = new Hand(false, null);
        assertThrows(IllegalStateException.class, hand::dirtyUp);
    }

    @Test
    void testCheckFingersCleanlinessWithNullFingers() {
        Hand hand = new Hand(false, null);
        assertThrows(IllegalStateException.class, hand::checkFingersCleanliness);
    }
}
