package com.study.demo;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Tiger
 * @date 2019-10-08
 * @see SellSort
 **/
public class SellSort {
    /**
     * 希尔排序
     *
     * @param array
     */
    public static void shellSort(int[] array) {
        int len = array.length;
        int temp, gap = len / 2;
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                temp = array[i];
                int preIndex = i - gap;
                while (preIndex >= 0 && array[preIndex] > temp) {
                    array[preIndex + gap] = array[preIndex];
                    preIndex -= gap;
                }
                array[preIndex + gap] = temp;
            }
            gap /= 2;
        }
    }

    public static void main(String[] args) {
        int[] array = new int[100];
        for (int i = 1; i < array.length; i++) {
            array[i] = new Random().nextInt(1000);
        }
        System.out.println("排序前：" + Arrays.toString(array));
        shellSort(array);
        System.out.println("排序后：" + Arrays.toString(array));
    }
}
