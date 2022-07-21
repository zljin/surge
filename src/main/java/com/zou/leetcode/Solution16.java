package com.zou.leetcode;

import java.util.Arrays;

/**
 * @author leonard
 * @date 2022/7/4
 * @Description https://leetcode.cn/problems/3sum-closest/
 */
public class Solution16 {
    public static void main(String[] args) {
        System.out.println(new Solution16().threeSumClosest(
                new int[]{-1, 2, 1, -4}, 1
        ));
    }

    //算法：排序加双指针
    private int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            int start = i + 1;
            int end = nums.length - 1;
            while (start < end) {
                int sum = nums[i] + nums[start] + nums[end];

                if (Math.abs(target - sum) < Math.abs(target - ans)) {
                    ans = sum;
                }

                if (sum > target) {
                    end--;
                } else if (sum < target) {
                    start++;
                } else {
                    return ans;
                }

            }
        }
        return ans;
    }
}
