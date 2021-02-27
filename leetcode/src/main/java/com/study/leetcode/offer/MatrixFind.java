package com.study.leetcode.offer;

/**
 * @author yhhu
 * @date 2021/2/27
 * @description
 */
public class MatrixFind {
    public boolean Find(int target, int[][] array) {
        for (int i = 0; i < array.length; i++) {
            int[] innerArray = array[i];
            for (int j = innerArray.length - 1; j >= 0; j--) {
                // 未到最后一行，并且目标比矩阵的数字大
                if (target > innerArray[j] && i < array.length - 1) {
                    break;
                }
                // 目标比当前值小，往左走
                if (target < innerArray[j]) {
                    continue;
                }
                return target == innerArray[j];
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] array = new int[4][4];
        array[0] = new int[]{1, 2, 8, 9};
        array[1] = new int[]{2, 4, 9, 12};
        array[2] = new int[]{4, 7, 10, 13};
        array[3] = new int[]{6, 8, 11, 15};
        MatrixFind matrixFind = new MatrixFind();
        System.out.println(matrixFind.Find(7, array));
    }
}
