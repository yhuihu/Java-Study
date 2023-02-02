package com.study.leetcode.offer;

/**
 * @author yanghuihu
 * @version 1.0.0
 * @description 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 12258
 * 输出: 5
 * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 * 提示：
 * <p>
 * 0 <= num < 231
 * Related Topics
 * 字符串
 * 动态规划
 * @date 2023/1/27 22:48
 */
public class Question46 {

    public static void main(String[] args) {
        System.out.println(translateNum(12258));
    }

    public static int translateNum(int num) {
        // 1.
        // 12258
        // 12258 % 100 = 58     > 25
        // 12258 / 10 = 1225   次数+1

        // 2.
        // 1225
        // 1225 % 100 = 25  <= 25
        // 1225 / 10 = 122  次数+1
        // 1225 / 100 = 12  次数+1


        if (num <= 9) {
            System.out.println(num);
            return 1;
        }
        int modNum = num % 100;
        if (modNum >= 26 || modNum <= 9) {
            return translateNum(num / 10);
        } else {
            return translateNum(num / 10) + translateNum(num / 100);
        }
    }
}
