package com.zou.leetcode;

/**
 * @author leonard
 * @date 2022/6/16
 * @Description https://leetcode.cn/problems/counting-bits/
 */
public class Solution338 {
    public static void main(String[] args) {
        System.out.println(new Solution338().countBits(3));
    }

    public int[] countBits(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i & (i - 1)] + 1;
        }
        return dp;
    }
}
