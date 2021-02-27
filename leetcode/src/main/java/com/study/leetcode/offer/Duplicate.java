package com.study.leetcode.offer;

import java.util.Arrays;

/**
 * @author yhhu
 * @date 2021/2/27
 * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复的。也不知道每个数字重复几次。
 * 请找出数组中第一个重复的数字。 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
 * 返回描述：
 * 如果数组中有重复的数字，函数返回true，否则返回false。
 * 如果数组中有重复的数字，把重复的数字放到参数duplication[0]中。（ps:duplication已经初始化，可以直接赋值使用。）
 */
public class Duplicate {
    public static boolean duplicate(int[] numbers, int length, int[] duplication) {
        if (numbers == null || length <= 0) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            int number = numbers[i];
            // 判断位置是否正确
            if (number != i) {
                // 正确位置上有相同数字
                if (number == numbers[number]) {
                    duplication[0] = number;
                    return true;
                }
                swap(numbers, i, number);
            }
        }
        return false;
    }

    private static void swap(int[] numbers, int i, int j) {
        // 不新建内存交换数字
        numbers[i] = numbers[i] + numbers[j];
        numbers[j] = numbers[i] - numbers[j];
        numbers[i] = numbers[i] - numbers[j];
    }

    public static void main(String[] args) {
        int[] numbers = new int[]{2, 3, 1, 0, 2, 5, 3};
        int[] duplication = new int[1];
        System.out.println(duplicate(numbers, numbers.length, duplication));
        System.out.println(Arrays.toString(duplication));
    }
}
