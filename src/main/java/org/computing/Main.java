package org.computing;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Введіть N (розмірність елементів): ");
        Scanner scanner = new Scanner(System.in);

        Data.N = scanner.nextInt();

        initializeData();

        T1 T1 = new T1();
        T2 T2 = new T2();
        T3 T3 = new T3();
        T4 T4 = new T4();

        T1.setPriority(Thread.MAX_PRIORITY);
        T2.setPriority(Thread.NORM_PRIORITY);
        T3.setPriority(Thread.NORM_PRIORITY);
        T4.setPriority(Thread.MIN_PRIORITY);

        long start = System.currentTimeMillis();

        T1.start();
        T2.start();
        T3.start();
        T4.start();

        try {
            T1.join();
            T2.join();
            T3.join();
            T4.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        long finish = System.currentTimeMillis();
        System.out.println("Час: " + (finish - start) + " мс");
    }

    private static void initializeData() {
        Data.Z = new int[Data.N];
        Data.MA = new int[Data.N][Data.N];
        Data.MX = new int[Data.N][Data.N];
        Data.MR = new int[Data.N][Data.N];
        Data.MC = new int[Data.N][Data.N];
        Data.a = 0;
        Data.b = Integer.MAX_VALUE;
    }
}