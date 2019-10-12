package sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Tiger
 * @date 2019-10-08
 * @see sort
 **/
public class SelectionSort {
    /**
     * 选择排序
     *
     * @param array
     */
    public static void selectionSort(int[] array) {
        if (array.length == 0) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i; j < array.length; j++) {
                //找到最小的数
                if (array[j] < array[minIndex]) {
                    //将最小数的索引保存
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = array[minIndex];
                array[minIndex] = array[i];
                array[i] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[100];
        for (int i = 1; i < array.length; i++) {
            array[i] = new Random().nextInt(100);
        }
        System.out.println("排序前：" + Arrays.toString(array));
        selectionSort(array);
        System.out.println("排序后：" + Arrays.toString(array));
    }
}
