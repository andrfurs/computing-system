package org.computing;

import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;

public class T3 extends Thread {
    @Override
    public void run() {
        int a3;
        int b3;
        int[][] MB3;
        int[][] MA3;
        int d3;

        int start = 2 * Data.N / Data.P;
        int end = 3 * Data.N / Data.P;

        System.out.println("T3 розпочався");

        try {
            Data.Sem1.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            Data.Sem2.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            Data.Sem4.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        a3 = Data.findMinVectorElement(Data.getPartOfVector(Data.Z, start, end));
        Data.compareWithMin(a3);

        try {
            Data.BarrierA.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }

        b3 = Data.findMaxVectorElement(Data.getPartOfVector(Data.Z, start, end));
        Data.compareWithMax(b3);

        try {
            Data.BarrierB.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }

        MB3 = Data.multiplyMatrixes(Data.MR, Data.getPartOfMatrix(Data.MC, start, end));

        a3 = Data.a;
        b3 = Data.b;
        synchronized (Data.CS){
            d3 = Data.d;
        }

        MA3 = Data.sumMatrixes(Data.multiplyNumMatrix(a3, Data.getPartOfMatrix(Data.MX, start, end)), Data.multiplyNumMatrix((b3 * d3), MB3));

        Data.joinResult(MA3, start, end);

        try {
            Data.Sem3.acquire(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (int[] i : Data.MA) {
            System.out.println(Arrays.toString(i));
        }

        System.out.println("T3 завершився");
    }
}
