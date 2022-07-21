package com.zou.leetcode;

/**
 * @author leonard
 * @date 2022/6/22
 * @Description https://leetcode.cn/problems/container-with-most-water/
 */
public class Solution11 {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(new Solution11().maxArea(arr));


    }

    //双指针
    public int maxArea(int[] arr) {
        int left = 0, right = arr.length - 1;
        int maxArea = 0;
        while (left < right) {
            int area = Math.min(arr[left], arr[right]) * (right - left);
            maxArea = Math.max(maxArea,area);
            if(arr[left]<=arr[right]){
                left++;
            }else {
                right--;
            }
        }
        return maxArea;
    }
}
