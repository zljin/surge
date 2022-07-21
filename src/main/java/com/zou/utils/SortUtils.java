package com.zou.utils;

public class SortUtils {

    /**
     * @Description: 插入排序
     * @version: v1.1.0
     * @author: zoulj
     * @date: 2021-04-21 23:31:40
     *
     * Modification History:
     * Date         Author          Version            Description
     *-------------------------------------------------------------
     * 2021-04-21 23:31:40      zoulj          v1.1.0               修改原因
     */
    public static void insertSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int temp = arr[i];
            int j;
            for (j = i - 1; j >= 0 && temp < arr[j]; j--) {
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = temp;
        }
    }

    /**
     * @Description: 冒泡排序
     * @version: v1.1.0
     * @author: zoulj
     * @date: 2021-04-21 23:31:57
     *
     * Modification History:
     * Date         Author          Version            Description
     *-------------------------------------------------------------
     * 2021-04-21 23:31:57      zoulj          v1.1.0               修改原因
     */
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {//n-1趟
            for (int j = i; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr[j], arr[j + 1]);
                }
            }
        }
    }


    /**
     * 选择排序
     * @param arr
     */
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] > arr[j]) {
                    swap(arr[i], arr[j]);
                }
            }
        }
    }

    private static void swap(int a, int b) {
        int t = a;
        a = b;
        b = t;
    }


    /**
     * 快速排序
     * @param arr
     */
    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int start, int end) {
        if (start > end) {
            return;
        } else {
            int base = divide(arr, start, end);
            quickSort(arr, 0, base - 1);
            quickSort(arr, base + 1, end);
        }
    }

    private static int divide(int[] arr, int start, int end) {
        int base = arr[end];
        while (start < end) {
            while (arr[start] <= base && start < end) {
                start++;
            }
            if (start < end) {
                int temp = arr[start];
                arr[start] = base;
                arr[end] = temp;
                end--;
            }
            while (arr[end] >= base && start < end) {
                end--;
            }
            if (start < end) {
                int temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
                start++;
            }
        }
        return end;
    }


    public static void mergeSort(int[] arr) {
        mergesort(arr, 0, arr.length - 1);
    }

    private static void mergesort(int[] arr, int left, int right) {
        if (left >= right)
            return;
        int center = (left + right) / 2;
        mergesort(arr, left, center);
        mergesort(arr, center + 1, right);
        merge(arr, left, center, right);
    }

    private static void merge(int[] arr, int left, int center, int right) {
        //临时数组
        int[] tmpArr = new int[arr.length];
        //右边第一个元素
        int mid = center + 1;
        //临时数组的第一个下标
        int i = left;
        // 缓存左数组第一个元素的索引
        int tmp = left;

        while (left <= center && mid <= right) {
            //两个数组取最小的放入临时数组
            if (arr[left] <= arr[mid]) {
                tmpArr[i++] = arr[left++];
            } else {
                tmpArr[i++] = arr[mid++];
            }
        }

        while (left <= center) {
            tmpArr[i++] = arr[left++];
        }

        while (mid <= right) {
            tmpArr[i++] = arr[mid++];
        }

        //将临时数组内容拷贝会原来的数组
        while (tmp <= right) {
            arr[tmp] = tmpArr[tmp];
            tmp++;
        }
    }

}
