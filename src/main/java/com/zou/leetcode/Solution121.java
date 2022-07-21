package com.zou.leetcode;

/**
 * @author leonard
 * @date 2022/6/10
 * @Description 动态规划
 * <p>
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/
 */
public class Solution121 {
    public static void main(String[] args) {
        int[] arr = new int[]{7, 1, 5, 3, 6, 4};
        System.out.println(profit(arr));
        System.out.println(profitDP(arr));
    }

    private static int profitDP(int[] arr) {
        int max = 0;
        //dp[i]：截止到i,价格的最低点是多少 dp[i] = min(dp[i-1],arr[i])
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            dp[i] = dp[i - 1] < arr[i] ? dp[i - 1] : arr[i];
            max = arr[i] - dp[i] > max ? arr[i] - dp[i] : max;
        }
        return max;
    }

    private static int profit(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int maxprofit = 0;
        for (int i = 0; i < arr.length; i++) {
            int profit = 0;
            for (int j = i + 1; j < arr.length; j++) {
                int dup = arr[j] - arr[i];
                if (dup > profit) {
                    profit = dup;
                }
            }
            if (maxprofit < profit) {
                maxprofit = profit;
            }
        }
        return maxprofit;
    }
}
