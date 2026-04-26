package org.computing;

import java.util.concurrent.BrokenBarrierException;

public class T1 extends Thread {
    @Override
    public void run() {
        int a1;
        int b1;
        int[][] MB1;
        int[][] MA1;
        int d1;

        int start = 0;
        int end = Data.N / Data.P;

        System.out.println("T1 розпочався");

        for (int i = 0; i < Data.N; i++) {
            Data.Z[i] = 1;
            for (int j = 0; j < Data.N; j++) {
                Data.MC[i][j] = 1;
            }
        }

        Data.Sem1.release(3);

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

        a1 = Data.findMinVectorElement(Data.getPartOfVector(Data.Z, start, end));
        Data.compareWithMin(a1);

        try {
            Data.BarrierA.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }

        b1 = Data.findMaxVectorElement(Data.getPartOfVector(Data.Z, start, end));
        Data.compareWithMax(b1);

        try {
            Data.BarrierB.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }

        MB1 = Data.multiplyMatrixes(Data.MR, Data.getPartOfMatrix(Data.MC, start, end));

        a1 = Data.a;
        b1 = Data.b;
        synchronized (Data.CS){
            d1 = Data.d;
        }

        MA1 = Data.sumMatrixes(Data.multiplyNumMatrix(a1, Data.getPartOfMatrix(Data.MX, start, end)), Data.multiplyNumMatrix((b1 * d1), MB1));

        Data.joinResult(MA1, start, end);

        Data.Sem3.release();

        System.out.println("T1 завершився");
    }
}
