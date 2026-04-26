package org.computing;

import java.util.concurrent.BrokenBarrierException;

public class T2 extends Thread {
    @Override
    public void run() {
        int a2;
        int b2;
        int[][] MB2;
        int[][] MA2;
        int d2;

        int start = Data.N / Data.P;
        int end = 2 * Data.N / Data.P;

        System.out.println("T2 розпочався");

        for (int i = 0; i < Data.N; i++) {
            for (int j = 0; j < Data.N; j++) {
                Data.MX[i][j] = 1;
            }
        }

        Data.Sem2.release(3);

        try {
            Data.Sem1.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            Data.Sem4.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        a2 = Data.findMinVectorElement(Data.getPartOfVector(Data.Z, start, end));
        Data.compareWithMin(a2);

        try {
            Data.BarrierA.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }

        b2 = Data.findMaxVectorElement(Data.getPartOfVector(Data.Z, start, end));
        Data.compareWithMax(b2);

        try {
            Data.BarrierB.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }

        MB2 = Data.multiplyMatrixes(Data.MR, Data.getPartOfMatrix(Data.MC, start, end));

        a2 = Data.a;
        b2 = Data.b;
        synchronized (Data.CS){
            d2 = Data.d;
        }

        MA2 = Data.sumMatrixes(Data.multiplyNumMatrix(a2, Data.getPartOfMatrix(Data.MX, start, end)), Data.multiplyNumMatrix((b2 * d2), MB2));

        Data.joinResult(MA2, start, end);

        Data.Sem3.release();

        System.out.println("T2 завершився");
    }
}
