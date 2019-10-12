package sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Tiger
 * @date 2019-10-08
 * @see BubbleSort
 **/
public class BubbleSort {
    /**
     * 冒泡排序
     *
     * @param array
     */
    public static void bubbleSort(int[] array) {
        int flag;
        if (array.length == 0) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            flag = 0;
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j + 1] < array[j]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                    flag = 1;
                }
            }
            if (flag == 0) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = new Random().nextInt(100);
        }
        System.out.println("排序前：" + Arrays.toString(array));
        bubbleSort(array);
        System.out.println("排序后：" + Arrays.toString(array));
    }
}
