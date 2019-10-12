package sort;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Tiger
 * @date 2019-10-08
 * @see sort
 **/
public class MergeSort {
    public static void sort(int[] arr) {
        int[] temp = new int[arr.length];
        //在排序前，先建好一个长度等于原数组长度的临时数组，
        //避免递归中频繁开辟空间
        sort(arr, 0, arr.length - 1, temp);
    }

    private static void sort(int[] arr, int left, int right, int[] temp) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        //左边归并排序，使得左子序列有序
        sort(arr, left, mid, temp);
        //右边归并排序，使得右子序列有序
        sort(arr, mid + 1, right, temp);
        //将两个有序子数组合并操作
        merge(arr, left, mid, right, temp);
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        //左序列指针
        int i = left;
        //右序列指针
        int j = mid + 1;
        //临时数组指针
        int t = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }
        //将左边剩余元素填充进temp中
        while (i <= mid) {
            temp[t++] = arr[i++];
        }
        //将右序列剩余元素填充进temp中
        while (j <= right) {
            temp[t++] = arr[j++];
        }
        t = 0;
        //将temp中的元素全部拷贝到原数组中
        while (left <= right) {
            arr[left++] = temp[t++];
        }
    }

    public static void main(String[] args) {
        int[] array = new int[100];
        for (int i = 1; i < array.length; i++) {
            array[i] = new Random().nextInt(1000);
        }
        System.out.println("排序前：" + Arrays.toString(array));
        sort(array);
        System.out.println("排序后：" + Arrays.toString(array));
    }
}
