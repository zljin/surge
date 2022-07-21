package com.zou.leetcode;

import java.util.Stack;

/**
 * @author leonard
 * @date 2022/6/15
 * @Description https://leetcode.cn/problems/valid-parentheses/
 */
public class Solution20 {
    public static void main(String[] args) {
        boolean valid = new Solution20().isValid("([])");
        System.out.println(valid);
    }

    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();

        for (Character c : s.toCharArray()) {
            if ('(' == c) {
                stack.push(')');
            } else if ('[' == c) {
                stack.push(']');
            } else if ('{' == c) {
                stack.push('}');
            } else if(stack.isEmpty() ||c != stack.pop()){//)))
                return false;
            }
        }
        return stack.isEmpty();
    }
}
