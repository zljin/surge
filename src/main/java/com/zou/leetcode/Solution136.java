package com.zou.leetcode;

/**
 * @author leonard
 * @date 2022/6/15
 * @Description https://leetcode.cn/problems/single-number/
 */
public class Solution136 {
    public static void main(String[] args) {

        System.out.println(1 ^ 2 ^ 3 ^ 4 ^ 5 ^ 2);
    }

    //方法1：简单粗暴： 直接通过map存结果，然后循环遍历

    //方法2：异或

    /**
     * 交换律：a ^ b ^ c <=> a ^ c ^ b
     * <p>
     * 任何数于0异或为任何数 0 ^ n => n
     * <p>
     * 相同的数异或为0: n ^ n => 0
     * <p>
     * var a = [2,3,2,4,4]
     * <p>
     * 2 ^ 3 ^ 2 ^ 4 ^ 4等价于 2 ^ 2 ^ 4 ^ 4 ^ 3 => 0 ^ 0 ^3 => 3
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int n : nums) {
            result ^= n;
        }
        return result;
    }
}
