package org.computing;

import java.util.concurrent.BrokenBarrierException;

public class T4 extends Thread {
    @Override
    public void run() {
        int a4;
        int b4;
        int[][] MB4;
        int[][] MA4;
        int d4;

        int start = 3 * Data.N / Data.P;
        int end = 4 * Data.N / Data.P;

        System.out.println("T4 розпочався");

        for (int i = 0; i < Data.N; i++) {
            for (int j = 0; j < Data.N; j++) {
                Data.MR[i][j] = 1;
            }
        }
        Data.d = 1;

        Data.Sem4.release(3);

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

        a4 = Data.findMinVectorElement(Data.getPartOfVector(Data.Z, start, end));
        Data.compareWithMin(a4);

        try {
            Data.BarrierA.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }

        b4 = Data.findMaxVectorElement(Data.getPartOfVector(Data.Z, start, end));
        Data.compareWithMax(b4);

        try {
            Data.BarrierB.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }

        MB4 = Data.multiplyMatrixes(Data.MR, Data.getPartOfMatrix(Data.MC, start, end));

        a4 = Data.a;
        b4 = Data.b;
        synchronized (Data.CS){
            d4 = Data.d;
        }

        MA4 = Data.sumMatrixes(Data.multiplyNumMatrix(a4, Data.getPartOfMatrix(Data.MX, start, end)), Data.multiplyNumMatrix((b4 * d4), MB4));

        Data.joinResult(MA4, start, end);

        Data.Sem3.release();

        System.out.println("T4 завершився");
    }
}
