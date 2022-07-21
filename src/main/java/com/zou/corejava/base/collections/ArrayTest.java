package com.zou.corejava.base.collections;

/**
 * Array 数组,其元素类型都相同,数组长度固定不变,必须初始化
 */
public class ArrayTest {
    public static void main(String[] args) {
        int[] arr = new int[5];
        int[] arr2 = new int[]{20, 68, 34, 22, 34};
        int[][] arr3 = new int[][]{
                {4,5,6,7},
                {8,9,10,11},
                {1,2}
        };

        System.out.println(arr3[0][3]);
        System.out.println(arr3[2][1]);
    }
}
