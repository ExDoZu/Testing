package com.mycompany.lab2.log;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.mycompany.lab2.CsvHandler;

public class LogTest {
    final double PRECISION = 1e-10;

    @BeforeAll
    private static void init() {
        CsvHandler.openFile("log.csv");
    }

    @ParameterizedTest
    // x, ln(x), log5(x)
    @CsvSource({ "1.0, 0.0, 0.0",
            "2.0, 0.6931471805599453, 0.43067655807339306",
            "3.0, 1.0986122886681098, 0.6826061944859854",
            "4.0, 1.3862943611198906, 0.8613531161467861",
            "0, NaN, NaN",
            "-1, NaN, NaN",
    })
    void TestLog5(double x, double expectedLn, double expectedLog5) {
        Ln ln = mock(Ln.class);
        when(ln.calc(x)).thenReturn(expectedLn);

        // base
        when(ln.calc(5)).thenReturn(Math.log(5));

        Log log5 = new Log(5, ln);

        assertEquals(expectedLog5, log5.calc(x), PRECISION);
    }

    @ParameterizedTest
    // x, ln(x), log10(x)
    @CsvSource({ "1.0, 0.0, 0.0",
            "2.0, 0.6931471805599453, 0.3010299956639812",
            "3.0, 1.0986122886681098, 0.47712125471966244",
            "4.0, 1.3862943611198906, 0.6020599913279624",
            "0, NaN, NaN",
            "-1, NaN, NaN",
    })
    void TestLog10(double x, double expectedLn, double expectedLog10) {
        Ln ln = mock(Ln.class);
        when(ln.calc(x)).thenReturn(expectedLn);

        // base
        when(ln.calc(10)).thenReturn(Math.log(10));

        Log log10 = new Log(10, ln);

        assertEquals(expectedLog10, log10.calc(x), PRECISION);
    }

    @AfterAll
    private static void close() {
        CsvHandler.closeAndSave();
    }

}
