package com.zou.leetcode;

/**
 * @author leonard
 * @date 2022/6/15
 * @Description https://leetcode.cn/problems/maximum-subarray/
 */
public class Solution53 {
    public static void main(String[] args) {
        int[] nums = new int[]{1};
        System.out.println(new Solution53().maxSubArray(nums));
    }

    /**
     *
     * 动态规划：将计算的结果存储起来，避免重复计算
     * 核心是找到初始状态和状态转移方程
     *
     * dp[i] = Math.max((dp[i-1]+nums[i]),nums[i])
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int max = nums[0];

        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max((dp[i - 1] + nums[i]), nums[i]);
            if (max < dp[i]) {
                max = dp[i];
            }
        }
        return max;
    }
}
