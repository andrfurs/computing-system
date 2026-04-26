package org.computing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataTest {
    @BeforeEach
    void setUp() {
        Data.a = 0;
        Data.b = Integer.MAX_VALUE;
    }

    @Test
    void testGetPartOfVector() {
        int[] vector = {0, 1, 2, 3, 4, 5};
        int[] part = Data.getPartOfVector(vector, 2, 4);
        assertArrayEquals(new int[]{2, 3}, part);
    }

    @Test
    void testGetPartOfMatrix() {
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8}
        };
        int[][] expected = {
                {2, 3},
                {6, 7}
        };
        int[][] part = Data.getPartOfMatrix(matrix, 1, 3);
        assertArrayEquals(expected, part);
    }

    @Test
    void testJoinResult() {
        Data.N = 4;
        Data.MA = new int[Data.N][Data.N];

        int[][] part = {
                {10, 11},
                {20, 21},
                {30, 31},
                {40, 41}
        };
        Data.joinResult(part, 2, 4);

        assertEquals(10, Data.MA[0][2]);
        assertEquals(11, Data.MA[0][3]);
        assertEquals(40, Data.MA[3][2]);
        assertEquals(41, Data.MA[3][3]);
    }


    @Test
    void testFindMaxVectorElement() {
        int[] vector = {1, 9, 5, 8};
        assertEquals(9, Data.findMaxVectorElement(vector));
    }

    @Test
    void testCompareWithMax() {
        Data.a = 100;
        Data.compareWithMax(150);
        assertEquals(150, Data.a);
    }

    @Test
    void testFindMinVectorElement() {
        int[] vector = {3, 1, 5, 9};
        assertEquals(1, Data.findMinVectorElement(vector));
    }

    @Test
    void testCompareWithMin() {
        Data.b = 100;
        Data.compareWithMin(50);
        assertEquals(50, Data.b);
    }

    @Test
    void testMultiplyNumMatrix() {
        int[][] m = {{1, 2}, {3, 4}};
        int[][] expected = {{2, 4}, {6, 8}};
        assertArrayEquals(expected, Data.multiplyNumMatrix(2, m));
    }

    @Test
    void testMultiplyMatrixes() {
        int[][] m1 = {{1, 2}, {3, 4}};
        int[][] m2 = {{5, 6}, {7, 8}};
        int[][] expected = {{19, 22}, {43, 50}};
        assertArrayEquals(expected, Data.multiplyMatrixes(m1, m2));
    }

    @Test
    void testSumMatrixes() {
        int[][] m1 = {{1, 1}, {1, 1}};
        int[][] m2 = {{2, 2}, {2, 2}};
        int[][] expected = {{3, 3}, {3, 3}};
        assertArrayEquals(expected, Data.sumMatrixes(m1, m2));
    }
}
