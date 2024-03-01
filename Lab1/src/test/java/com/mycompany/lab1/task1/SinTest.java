package com.mycompany.lab1.task1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SinTest {
    double eps = 0.0001;

    @ParameterizedTest
    @ValueSource(doubles = { -Math.PI / 4, -Math.PI / 6 })
    void negativeCheck(double value) {
        assertEquals(Math.sin(value), Sin.sin(value), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = { -0.01, -0.001 })
    void negativeZeroCloseCheck(double value) {
        assertEquals(Math.sin(value), Sin.sin(value), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = { 0.01, 0.001 })
    void positiveZeroCloseCheck(double value) {
        assertEquals(Math.sin(value), Sin.sin(value), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = { Math.PI / 6, Math.PI / 4})
    void positiveCheck(double value) {
        assertEquals(Math.sin(value), Sin.sin(value), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = { 0.0, Math.PI, -Math.PI })
    void zeroCheck(double value) {
        assertEquals(Math.sin(value), Sin.sin(value), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = { Math.PI / 2, Math.PI / 2 + 0.001, Math.PI / 2 - 0.001 })
    void plusOneCheck(double value) {
        assertEquals(Math.sin(value), Sin.sin(value), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = { -Math.PI / 2, -Math.PI / 2 + 0.001, -Math.PI / 2 - 0.001 })
    void minusOneCheck(double value) {
        assertEquals(Math.sin(value), Sin.sin(value), eps);
    }

    @ParameterizedTest
    @ValueSource(doubles = { -Math.PI * 5.3, -Math.PI * 3.6, Math.PI * 3.1, Math.PI * 5.7 })
    void bigCheck(double value) {
        assertEquals(Math.sin(value), Sin.sin(value), eps);
    }
}

