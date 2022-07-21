package com.zou.leetcode;

import com.zou.utils.ZUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author leonard
 * @date 2022/6/10
 * @Description
https://leetcode.cn/problems/two-sum/
 */
public class Solution1 {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        //int[] ints = twoSum(nums, target);
        int[] ints = twoSumIndex(nums, target);
        ZUtil.printArr(ints);
    }


    private static int[] twoSum(int[] nums, int target) {
        //key:element of array  value:index
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (countMap.containsKey(target - nums[i])) {
                return new int[]{countMap.get(target-nums[i]),i};
            }
            countMap.put(nums[i], i);
        }
        return new int[]{};
    }


    //双指针
    private static int[] twoSumIndex(int[] nums,int target){
        int left  = 0;
        int right = nums.length-1;
        while(left<right){
            int temp = nums[left]+nums[right];
            if(target == temp){
                return new int[]{left,right};
            }else if(target>temp){
                left++;
            }else {
                right--;
            }
        }
        return new int[]{};
    }




}
