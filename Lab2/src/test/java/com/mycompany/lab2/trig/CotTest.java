package com.mycompany.lab2.trig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.mycompany.lab2.CsvHandler;

public class CotTest {
    @BeforeAll
    private static void init() {
        CsvHandler.openFile("cot.csv");
    }

    @AfterAll
    private static void close() {
        CsvHandler.closeAndSave();
    }

    final double PRECISION = 1e-10;

    @ParameterizedTest
    // x, sin(x), cos(x), cot(x)
    @CsvSource({
            "0.0, 0.0, 1.0, NaN",
            "1.5707963267948966, 1.0, 1.923132169163975e-17, 1.923132169163975e-17",
            "3.141592653589793, 0.0, -1.0, NaN",
            "4.71238898038469, -1.0, 1.423060349250807e-16, -1.423060349250807e-16",
            "-1.0, -0.8414709848078965, 0.5403023058681398, -0.6420926159343307",
            "-2.0, -0.9092974268256817, -0.4161468365471424, 0.4576575543602858",
            "-3.0, -0.1411200080598672, -0.9899924966004454, 7.015252551434533",
    })
    void TestCot(double x, double expectedSin, double expectedCos, double expectedCot) {
        Sin sin = mock(Sin.class);
        when(sin.calc(x)).thenReturn(expectedSin);
        Cos cos = mock(Cos.class);
        when(cos.calc(x)).thenReturn(expectedCos);

        Cot cot = new Cot(sin, cos);

        assertEquals(expectedCot, cot.calc(x), PRECISION);
    }
}
