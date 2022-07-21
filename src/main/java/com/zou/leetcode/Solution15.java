package com.zou.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author leonard
 * @date 2022/6/22
 * @Description https://leetcode.cn/problems/3sum/
 */
public class Solution15 {
    public static void main(String[] args) {
       //int[] arr = new int[]{-1, 0, 1, 2, -1, -4};
        int[] arr = new int[]{-2,0,0,2,2};
        System.out.println(new Solution15().threeSum(arr));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) return resultList;
            //去重1
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int cur = nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int tmp = cur + nums[left] + nums[right];
                if (tmp == 0) {
                    List<Integer> tempList = new ArrayList<>();
                    tempList.add(cur);
                    tempList.add(nums[left]);
                    tempList.add(nums[right]);
                    resultList.add(tempList);

                    //去重二
                    while(left < right && nums[left+1] == nums[left]) left++;
                    while (left < right && nums[right-1] == nums[right]) --right;

                    left++;
                    right--;
                } else if (tmp > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return resultList;
    }
}
