package com.zou.leetcode;

import java.util.*;

/**
 * @author leonard
 * @date 2022/6/19
 * @Description https://leetcode.cn/problems/find-all-numbers-disappeared-in-an-array/
 */
public class Solution448 {
    public static void main(String[] args) {


    }

    //比较粗暴的方法
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        List<Integer> input = new ArrayList<>();
        Set<Integer> inputSet = new HashSet<>();
        Arrays.sort(nums);
        for (int num : nums) {
            input.add(num);
            inputSet.add(num);
        }
        Integer max = input.get(input.size() - 1);

        if (nums.length >= max) { //[1,1] or [1,1,4,4] or [1,1,1,6,6,6]

            for (int i = 1; i <= nums.length; i++) {
                if (inputSet.contains(i)) {
                    continue;
                }
                res.add(i);
            }

        } else {
            for (int i = 1; i <= max; i++) {
                if (input.contains(i)) {
                    continue;
                }
                res.add(i);
            }
        }


        return res;
    }
}
