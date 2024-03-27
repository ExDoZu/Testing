package com.mycompany.lab1.task2;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import com.mycompany.lab1.task2.ClosedHash.Methods;

class ClosedHashTest {

    @ParameterizedTest
    @EnumSource(ClosedHash.Methods.class)
    void testAddAndGet(ClosedHash.Methods method) {
        ClosedHash<String, Integer> hashTable = new ClosedHash<>(method);
        hashTable.add("apple", 42);

        assertEquals(42, hashTable.get("apple"));
    }

    @ParameterizedTest
    @EnumSource(ClosedHash.Methods.class)
    void testRemove(ClosedHash.Methods method) {
        ClosedHash<String, Integer> hashTable = new ClosedHash<>(method);
        hashTable.add("banana", 17);
        hashTable.remove("banana");

        assertNull(hashTable.get("banana"));
    }

    @ParameterizedTest
    @EnumSource(value = Methods.class, names = { "LINEAR", "QUADRATIC" })
    void simpleFilling(Methods method) {
        ClosedHash<Integer, String> map = new ClosedHash<>(method);
        map.add(7, "lol");
        assertEquals("lol", map.get(7));
        assertNull(map.get(8));
        assertNull(map.get(6));
        map.add(7, "kek");
        assertEquals("lol", map.get(7));
        map.add(8, "wow");
        assertEquals("wow", map.get(8));

        map.remove(7);
        assertEquals("kek", map.get(7));
        assertEquals(
                "{null, null, null, null, null, null, null, null, 7:kek, 8:wow, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}",
                map.toString());

    }

    @ParameterizedTest
    @EnumSource(ClosedHash.Methods.class)
    void fullFilling(ClosedHash.Methods method) {
        ClosedHash<Integer, String> map = new ClosedHash<>(method);
        for (int i = 0; i < map.getMaxSize(); i++) {
            map.add(i, "uwu");
        }
        map.add(7, "qwerty");
        assertEquals(
                "{0:uwu, 1:uwu, 2:uwu, 3:uwu, 4:uwu, 5:uwu, 6:uwu, 7:uwu, 8:uwu, 9:uwu, 10:uwu, 11:uwu, 12:uwu, 13:uwu, 14:uwu, 15:uwu, 16:uwu, 17:uwu, 18:uwu, 19:uwu, 20:uwu, 21:uwu, 22:uwu, 23:uwu, 24:uwu, 25:uwu, 26:uwu, 27:uwu, 28:uwu}",
                map.toString());

    }

    @ParameterizedTest
    @EnumSource(ClosedHash.Methods.class)
    void testToString(ClosedHash.Methods method) {
        ClosedHash<Integer, String> map = new ClosedHash<>(method);
        map.add(1, "one");
        map.add(2, "two");
        map.add(3, "three");

        String expected = "{null, 1:one, 2:two, 3:three, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}";
        assertEquals(expected, map.toString());
    }

    @ParameterizedTest
    @EnumSource(ClosedHash.Methods.class)
    void testCollisionHandling(ClosedHash.Methods method) {
        ClosedHash<Integer, String> map = new ClosedHash<>(method);
        map.add(1, "one");
        map.add(30, "thirty");

        assertEquals("one", map.get(1));
        assertEquals("thirty", map.get(30));
    }

    ClosedHash<Integer, String> repeatedMap = new ClosedHash<>(Methods.LINEAR);

    @RepeatedTest(4)
    @ParameterizedTest
    @EnumSource(ClosedHash.Methods.class)
    void testCollisionHandlingRepeated() {

        repeatedMap.add(1, "one");
        repeatedMap.add(30, "thirty");

        assertEquals("one", repeatedMap.get(1));
        assertEquals("thirty", repeatedMap.get(30));
    }

    @Test
    void testQuadricManyAdd() {
        ClosedHash<Integer, String> map = new ClosedHash<>(Methods.QUADRATIC);
        for (int i = 0; i < 4; i++) {
            map.add(2, "googol");
        }
        assertEquals(
                "{null, null, 2:googol, 2:googol, null, null, 2:googol, null, null, null, null, 2:googol, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}",
                map.toString());
    }

    @Test
    void testQuadricVeryManyAdd() {
        ClosedHash<Integer, String> map = new ClosedHash<>(Methods.QUADRATIC);
        for (int i = 0; i < map.getMaxSize() + 10; i++) {
            map.add(1, "googol");
        }
        assertEquals(
                "{1:googol, 1:googol, 1:googol, null, null, 1:googol, 1:googol, 1:googol, 1:googol, null, 1:googol, null, null, null, 1:googol, null, null, 1:googol, null, null, null, 1:googol, null, 1:googol, 1:googol, 1:googol, 1:googol, null, null}",
                map.toString());
    }

    @Test

    void nullKey() {
        ClosedHash<Integer, String> map = new ClosedHash<>(Methods.QUADRATIC);

        NullPointerException thrown = Assertions.assertThrows(NullPointerException.class, () -> {
            map.add(null, "bzbz");
        }, "Not null key was expected");

        assertEquals("Null key", thrown.getMessage());

    }

}