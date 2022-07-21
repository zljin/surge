package com.zou.leetcode;

/**
 * @author leonard
 * @date 2022/6/15
 * @Description https://leetcode.cn/problems/climbing-stairs/
 */
public class Solution70 {
    public static void main(String[] args) {
        System.out.println(new Solution70().climbStairs(3));
    }

    //初始状态和状态转移方程
    public int climbStairs(int n){
        if(n==1) return 1;
        if(n==2) return 2;
        //if(n==3) return 3;
        //dp[n] = dp[n-1] + dp[n-2]
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;

        for(int i = 3;i<=n;i++){
            dp[i] = dp[i-1]+dp[i-2];
        }

        return dp[n];
    }
}
