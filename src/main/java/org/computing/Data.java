package org.computing;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Data {
    public static int N;
    public static int P = 4;
    public static int[][] MA;
    public static int[] Z;
    public static int[][] MX;
    public static int[][] MR;
    public static int[][] MC;
    public static int d;
    public static volatile int a;
    public static volatile int b;

    public static Semaphore Sem1 = new Semaphore(0, true);
    public static Semaphore Sem2 = new Semaphore(0, true);
    public static Semaphore Sem3 = new Semaphore(0, true);
    public static Semaphore Sem4 = new Semaphore(0, true);

    public static final CyclicBarrier BarrierA = new CyclicBarrier(P);
    public static final CyclicBarrier BarrierB = new CyclicBarrier(P);

    public static final Object CS = new Object();

    public static int[] getPartOfVector(int[] vector, int start, int end) {
        int[] partOfVector = new int[end - start];
        int index = 0;
        for (int i = start; i < end; i++) {
            partOfVector[index] = vector[i];
            index++;
        }
        return partOfVector;
    }

    public static int[][] getPartOfMatrix(int[][] matrix, int start, int end) {
        int[][] partOfMatrix = new int[matrix.length][end - start];
        for (int i = 0; i < matrix.length; i++) {
            int index = 0;
            for (int j = start; j < end; j++) {
                partOfMatrix[i][index] = matrix[i][j];
                index++;
            }
        }
        return partOfMatrix;
    }

    public static void joinResult(int[][] matrix, int start, int end) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                MA[i][start + j] = matrix[i][j];
            }
        }
    }

    public static int findMaxVectorElement(int[] vector) {
        int max = 0;
        for (int i : vector) {
            if (max < i) {
                max = i;
            }
        }
        return max;
    }

    public static void compareWithMax(int num)
    {
        if (num > a)
        {
            a = num;
        }
    }

    public static int findMinVectorElement(int[] vector) {
        int min = (int) vector[0];
        for (int i : vector) {
            if (min > i) {
                min = i;
            }
        }
        return min;
    }

    public static synchronized void compareWithMin(int num)
    {
        if (num < b)
        {
            b = num;
        }
    }

    public static int[][] multiplyNumMatrix(int num, int[][] matrix) {
        int[][] res = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                res[i][j] = matrix[i][j] * num;
            }
        }
        return res;
    }

    public static int[][] multiplyMatrixes(int[][] matrix1, int[][] matrix2) {
        int[][] res = new int[matrix1.length][matrix2[0].length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j <matrix2[0].length; j++) {
                for (int k = 0; k < matrix1[0].length; k++) {
                    res[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return res;
    }

    public static int[][] sumMatrixes(int[][] matrix1, int[][] matrix2) {
        int[][] sum = new int[matrix1.length][matrix1[0].length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix1[0].length; j++) {
                sum[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return sum;
    }
}
