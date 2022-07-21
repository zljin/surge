package com.zou.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author leonard
 * @date 2022/6/12
 * @Description 滑动窗口算法
 * <p>
https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 */
public class Solution3 {

    public static void main(String[] args) {
        int re = new Solution3().lengthOfLongestSubstring("bcfeacaaabbcc");
        System.out.println(re);
    }

    /**
     *
     * @param s  bcfeacaaabbcc
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) {
            return 0;
        }
        //key:char value:index
        Map<Character, Integer> ret = new HashMap<>();
        int max = 0;//最长字串的长度
        int left = 0;//滑动窗口左下标，i相当于滑动窗口的右下标
        for (int i = 0; i < s.length(); i++) {
            if (ret.containsKey(s.charAt(i))) {
                left = Math.max(left, ret.get(s.charAt(i)) + 1);
            }
            ret.put(s.charAt(i), i);
            max = Math.max(max, i - left + 1);
        }

        return max;
    }

}
