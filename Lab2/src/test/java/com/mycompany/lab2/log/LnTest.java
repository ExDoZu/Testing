package com.mycompany.lab2.log;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.mycompany.lab2.CsvHandler;

public class LnTest {
    final double PRECISION = 1e-9;

    @BeforeAll
    private static void init() {
        CsvHandler.openFile("ln.csv");
    }

    @ParameterizedTest
    @CsvSource({
            "-1, NaN",
            "0.0, NaN",
            "0.1, -2.3025850929940455",
            "0.5, -0.6931471805599453",
            "1, 0.0",
            "2, 0.6931471805599453",
            "10, 2.302585092994046"
    })
    void TestLn(double x, double expected) {

        Ln ln = new Ln(PRECISION / 10);
        assertEquals(expected, ln.calc(x), PRECISION);
    }

    @AfterAll
    private static void close() {
        CsvHandler.closeAndSave();
    }

}
