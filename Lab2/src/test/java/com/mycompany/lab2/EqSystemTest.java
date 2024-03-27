package com.mycompany.lab2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.mycompany.lab2.log.Ln;
import com.mycompany.lab2.log.Log;
import com.mycompany.lab2.trig.Cot;

public class EqSystemTest {
    @BeforeAll
    private static void init() {
        CsvHandler.openFile("EqSystem.csv");
    }

    @AfterAll
    private static void close() {
        CsvHandler.closeAndSave();
    }

    final double PRECISION = 1e-10;

    @ParameterizedTest
    // x, cot(x), ln(x), log5(x), log10(x), Result
    @CsvSource({
            "-5.759586531581287, 1.749199854809259, NaN, NaN, NaN, 1.749199854809259",
            "-3.141592653589793, NaN, NaN, NaN, NaN, NaN",
            "-2.356194490192345, 1.0, NaN, NaN, NaN,  1.0",
            "0.0, NaN, NaN, NaN, NaN, NaN",
            "0.7853981633974483, 1.0, -0.2415644752704905,-0.150092447434117,-0.104910118633829, 14.1922451213987",
            "1.5707963267948966, 1.923132169163975e-17, 0.45158270528945485, 0.28058411063927590, 0.19611987703015265, 13.46053856766135",
            "2.356194490192345, -1.0, 0.8570478133976193, 0.5325137470518682, 0.3722111360858339, 13.03251767219308",
            "3.141592653589793, NaN, 1.1447298858494002, 0.7112606687126689, 0.4971498726941338, 12.72883201392398",
    })
    void TestEqSystem(double x, double expectedCot, double expectedLn, double expectedLog5, double expectedLog10,
            double result) {
        Cot cot = mock(Cot.class);
        when(cot.calc(x)).thenReturn(expectedCot);
        Ln ln = mock(Ln.class);
        when(ln.calc(x)).thenReturn(expectedLn);
        Log log5 = mock(Log.class);
        when(log5.calc(x)).thenReturn(expectedLog5);
        Log log10 = mock(Log.class);
        when(log10.calc(x)).thenReturn(expectedLog10);

        EqSystem eqSystem = new EqSystem(ln, log5, log10, cot);
        assertEquals(result, eqSystem.calc(x), PRECISION);

    }

}
