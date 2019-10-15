package com.study.demo;

import java.util.Arrays;
import java.util.Random;

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
            //为a[i]在前面的a[0...i-1]有序区间中找一个合适的位置
            for (j = i - 1; j >= 0; j--) {
                if (array[j] < array[i]) {
                    break;
                }
            }
            //如找到了一个合适的位置
            if (j != i - 1) {
                //将比a[i]大的数据向后移
                int temp = array[i];
                for (k = i - 1; k > j; k--) {
                    array[k + 1] = array[k];
                }
                //将a[i]放到正确位置上
                array[k + 1] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[100];
        for (int i = 1; i < array.length; i++) {
            array[i] = new Random().nextInt(100);
        }
        System.out.println("排序前：" + Arrays.toString(array));
        insertionSort(array);
        System.out.println("排序后：" + Arrays.toString(array));
    }
}
