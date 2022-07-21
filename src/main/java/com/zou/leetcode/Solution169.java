package com.zou.leetcode;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author leonard
 * @date 2022/6/16
 * @Description https://leetcode.cn/problems/majority-element/solution/
 */
public class Solution169 {
    public static void main(String[] args) {

    }

    public int majorityElement(Integer[] nums) {
        Arrays.sort(nums, Collections.reverseOrder());
        return nums[nums.length / 2];
    }
}
