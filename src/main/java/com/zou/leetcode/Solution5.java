package com.zou.leetcode;

import org.springframework.util.StringUtils;

/**
 * @author leonard
 * @date 2022/6/12
 * @Description https://leetcode.cn/problems/longest-palindromic-substring/
 */
public class Solution5 {
    public static void main(String[] args) {
        String s = new Solution5().longestPalindrome("");
        System.out.println(s);
    }

    public String longestPalindrome(String s) {
        if (StringUtils.isEmpty(s) || s.length() < 2) return s;

        int maxLeft = 0;
        int maxRight = 0;
        int maxLen = 1;

        /**
         * dp[l][r]: 字符串从 i 到 j 这段是否为回文
         *
         * 动态规划就是为了减少重复计算的问题
         * 说白了就是空间换时间，将计算结果暂存起来，避免重复计算
         * 作用和工程中用 redis 做缓存有异曲同工之妙
         *
         *
         * 动态规划关键是找到初始状态和状态转移方程
         * 初始状态，l=r 时，此时 dp[l][r]=true
         * 状态转移方程，dp[l][r]=true 并且(l-1)和（r+1)两个位置为相同的字符，此时 dp[l-1][r+1]=true
         */
        boolean[][] dp = new boolean[s.length()][s.length()];


        for (int r = 1; r < s.length(); r++) {
            for (int l = 0; l < r; l++) {
                if (s.charAt(l) == s.charAt(r) && (r - l <= 2 || dp[l + 1][r - 1])) {
                    dp[l][r] = true;
                    if (r - l + 1 > maxLen) {
                        maxLen = r - l + 1;
                        maxLeft = l;
                        maxRight = r;
                    }
                }
            }
        }

        return s.substring(maxLeft, maxRight + 1);
    }


}
