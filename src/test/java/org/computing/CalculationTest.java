package org.computing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculationTest {
    @Test
    void testCalculation() throws InterruptedException {
        Data.N = 55;
        Data.P = 4;
        Data.Z = new int[Data.N];
        Data.MA = new int[Data.N][Data.N];
        Data.MX = new int[Data.N][Data.N];
        Data.MR = new int[Data.N][Data.N];
        Data.MC = new int[Data.N][Data.N];
        Data.a = 0;
        Data.b = Integer.MAX_VALUE;

        T1 t1 = new T1();
        T2 t2 = new T2();
        T3 t3 = new T3();
        T4 t4 = new T4();

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        for (int i = 0; i < Data.N; i++) {
            for (int j = 0; j < Data.N; j++) {
                assertEquals(56, Data.MA[i][j]);
            }
        }
    }
}
