package com.study.demo;

import java.util.Arrays;

/**
 * @author Tiger
 * @date 2019-10-08
 * @see InsertionSort
 **/
public class InsertionSort {
    /**
     * 插入排序
     *
     * @param array
     */
    public static void insertionSort(int[] array) {
        int i, j, k;
        for (i = 1; i < array.length; i++) {
            int temp = array[i];
            for (j = i - 1; j >= 0; j--) {
                if (array[j] > temp) {
                    array[j + 1] = array[j];
                } else {
                    break;
                }
            }
            array[j + 1] = temp;
        }
    }

    public static void main(String[] args) {
//        int[] array = new int[100];
//        for (int i = 1; i < array.length; i++) {
//            array[i] = new Random().nextInt(100);
//        }
        int[] array = {66, 45, 78, 64, 52, 11, 64, 55, 99, 11, 18};
        System.out.println("排序前：" + Arrays.toString(array));
        insertionSort(array);
        System.out.println("排序后：" + Arrays.toString(array));
    }
}
