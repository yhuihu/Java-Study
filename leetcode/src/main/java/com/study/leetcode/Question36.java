package com.study.leetcode;

/**
 * @author yanghuihu
 * @version 1.0.0
 * @description 请你判断一个 9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
 * @date 2022/9/11 21:47
 */
public class Question36 {

    public static void main(String[] args) {
        System.out.println(2 / 3 * 3 + 9 / 3);
    }

    public boolean isValidSudoku(char[][] board) {
        // 记录某行，某位数字是否已经被摆放
        boolean[][] row = new boolean[9][9];
        // 记录某列，某位数字是否已经被摆放
        boolean[][] col = new boolean[9][9];
        // 记录某 3x3 宫格内，某位数字是否已经被摆放
        boolean[][][] subBoxes = new boolean[3][3][9];

        // 横坐标遍历
        for (int i = 0; i < board.length; i++) {
            // 纵坐标遍历
            for (int j = 0; j < board[i].length; j++) {
                // 为空直接跳过就好了
                if (board[i][j] != '.') {
                    // 判断元素是否在横、纵坐标、3X3内存在
                    int realNum = board[i][j] - '1';
                    boolean numExist = row[i][realNum] || col[j][realNum];

                    int subBoxRowIndex = i / 3;
                    int subBoxColIndex = j / 3;
                    boolean boxExist = subBoxes[subBoxRowIndex][subBoxColIndex][realNum];


                    if (numExist || boxExist) {
                        return false;
                    } else {
                        row[i][realNum] = true;
                        col[j][realNum] = true;
                        subBoxes[subBoxRowIndex][subBoxColIndex][realNum] = true;
                    }
                }
            }
        }
        return true;
    }

}
