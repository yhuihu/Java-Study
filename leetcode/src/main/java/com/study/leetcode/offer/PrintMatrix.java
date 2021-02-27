package com.study.leetcode.offer;

import java.util.ArrayList;

/**
 * @author yhhu
 * @date 2021/2/27
 * @description
 */
public class PrintMatrix {
    public ArrayList<Integer> printMatrix(int[][] matrix) {
        // 行
        int row = matrix.length;
        // 列
        int col = matrix[0].length;
        ArrayList<Integer> list = new ArrayList<>();
        // 定义上下左右初始位置
        int top = 0, bottom = row - 1, left = 0, right = col - 1;
        while (top <= bottom && left <= right) {
            // 上边左向右
            for (int j = left; j < right; j++) {
                list.add(matrix[top][j]);
            }
            // 右边上往下
            for (int j = top; j < bottom; j++) {
                list.add(matrix[j][right]);
            }
            // 下边右往左
            for (int j = right; j > left; j--) {
                list.add(matrix[bottom][j]);
            }
            // 左边下往上
            for (int j = bottom; j > top; j--) {
                list.add(matrix[j][left]);
            }
            // 指针移动
            top++;
            bottom--;
            left++;
            right--;
        }
        return list;
    }

    /**
     *  1  2  3  4
     *  5  6  7  8
     *  9 10 11 12
     * 13 14 15 16
     * @param args
     */
    public static void main(String[] args) {
        PrintMatrix matrix = new PrintMatrix();
        int[][] array = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8},{9, 10, 11, 12},{13, 14, 15, 16}};
        System.out.println(matrix.printMatrix(array));
    }
}
