package com.study.leetcode.offer;

import java.util.ArrayList;

/**
 * @author yhhu
 * @date 2021/2/27
 * @description
 */
public class PrintMatrix {
    public ArrayList<Integer> printMatrix(int[][] matrix) {
        ArrayList<Integer> list = new ArrayList<>();
        // 定义四个角落初始位置
        int topLeftStart = 0, topRightStart = matrix.length - 1, bottomLeftStart = 0, bottomRightStart = matrix[0].length - 1;
        while (topLeftStart <= topRightStart && bottomLeftStart <= bottomRightStart) {
            // 上边左向右
            for (int j = topLeftStart; j < bottomRightStart; j++) {
                list.add(matrix[topLeftStart][j]);
            }
            // 右边上往下
            for (int j = topLeftStart; j < topRightStart; j++) {
                list.add(matrix[j][bottomRightStart]);
            }
            // 下边右往左
            for (int j = bottomRightStart; j > bottomLeftStart; j--) {
                list.add(matrix[bottomLeftStart][j]);
            }
            for (int j = bottomLeftStart; j > topLeftStart; j--) {
                list.add(matrix[topLeftStart][j]);
            }
            // 指针移动
            topLeftStart++;
            topRightStart--;
            bottomLeftStart++;
            bottomRightStart--;
        }
        return list;
    }

    public static void main(String[] args) {
        PrintMatrix matrix = new PrintMatrix();
        int[][] array = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}};
        System.out.println(matrix.printMatrix(array));
    }
}
