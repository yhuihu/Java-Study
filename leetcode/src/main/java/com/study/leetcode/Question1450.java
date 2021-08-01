package com.study.leetcode;

/**
 * @author yhhu
 * @date 2021/6/27
 * @description
 */
public class Question1450 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] ints = new int[]{1, 2, 3};
        int[] ints1 = new int[]{4, 5, 6};
        solution.busyStudent(ints, ints1, 4);
    }

    static class Solution {
        public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
            int count = 0;
            for (int i = 0; i < startTime.length; i++) {
                if (startTime[i] > queryTime) {
                    continue;
                }
                if (endTime[i] >= queryTime) {
                    count++;
                }
            }
            return count;
        }
    }
}
