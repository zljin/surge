package com.zou.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leonard
 * @date 2022/7/7
 * @Description https://leetcode.cn/problems/generate-parentheses/
 */
public class Solution22 {
    public static void main(String[] args) {

    }
    //题解:https://leetcode.cn/problems/generate-parentheses/solution/sui-ran-bu-shi-zui-xiu-de-dan-zhi-shao-n-0yt3/

    List<String> res = new ArrayList<>();

    public List<String> getParenthesis(int n) {
        if (n <= 0) {
            return null;
        }

        def("",0,0,n);

        return res;
    }

    private void def(String paths, int left, int right, int n) {
        if (left > n || right > left) return;
        if (paths.length() == n * 2) {
            res.add(paths);
            return;
        }
        def(paths + "(", left + 1, right, n);
        def(paths + ")", left, right + 1, n);
    }


}
