package com.study.leetcode;

/**
 * @author yhhu
 * @date 2021/6/6
 * @description
 */
public class Question52 {
    /**
     * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     * <p>
     * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
     * <p>
     * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/n-queens
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */

    int allRow = 0;
    int solveCount = 0;

    public int totalNQueens(int n) {
        allRow = n;
        // 构建棋盘
        char queueSolve[][] = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                queueSolve[i][j] = '0';
            }
        }
        // 遍历棋盘放置皇后
        putQueue(queueSolve, 0);
        return solveCount;
    }

    public void putQueue(char[][] queueChar, int row) {
        if (row == allRow) {
            // 满足皇后数量，成功案例
            for (int i = 0; i < allRow; i++) {
                System.out.println(queueChar[i]);
            }
            System.out.println("------------------------------");
            solveCount++;
            return;
        }
        // 遍历列
        for (int i = 0; i < allRow; i++) {
            if (checkQueue(queueChar, row, i)) {
                queueChar[row][i] = '1';
                putQueue(queueChar, row + 1);
                queueChar[row][i] = '0';
            }
        }
    }

    public boolean checkQueue(char[][] queueChar, int row, int col) {
        short step = 1;
        // 只需要判断当前皇后的左上、右上、正上方是否存在皇后
        while (row >= step) {
            // 正上方判断
            if (queueChar[row - step][col] == '1') {
                return false;
            }
            // 左上方判断
            if ((col - step >= 0) && queueChar[row - step][col - step] == '1') {
                return false;
            }
            // 右上方判断
            if ((col + step < allRow) && queueChar[row - step][col + step] == '1') {
                return false;
            }
            step++;
        }
        return true;
    }

    public static void main(String[] args) {
        Question52 question52 = new Question52();
        System.out.println(question52.totalNQueens(4));
    }
}
