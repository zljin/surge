package com.zou.utils;

import com.zou.dataobject.ListNode;

/**
 * @author leonard
 * @date 2022/6/10
 * @Description 各种杂七杂八工具类
 */
public class ZUtil {
    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void printListNodes(ListNode<Integer> head){
        ListNode<Integer> cur = head;
        while(cur.getNext()!=null){
            System.out.print(cur.getVal()+"->");
            cur = cur.getNext();
        }
        System.out.println(cur.getVal());
    }


    //冒泡排序
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

    public static void bubbleSort(int[] arr,String desc) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {//n-1趟
            for (int j = i; j < n - i - 1; j++) {
                if (arr[j] < arr[j + 1]) {
                    swap(arr[j], arr[j + 1]);
                }
            }
        }
    }


    private static void swap(int a, int b) {
        int t = a;
        a = b;
        b = t;
    }


}
