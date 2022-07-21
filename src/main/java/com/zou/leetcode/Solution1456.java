package com.zou.leetcode;

/**
 * @author leonard
 * @date 2022/6/12
 * <p>
 * 滑动窗口算法
 * @Description https://leetcode-cn.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/
 */
public class Solution1456 {
    public static void main(String[] args) {

        int re = new Solution1456().maxVowels("abciiidef", 3);
        System.out.println(re);

    }

    /**
     * @param s abciiidef
     * @param k 固定的窗口大小
     * @return
     */
    public int maxVowels(String s, int k) {
        int left = 0;//定义左指针
        int count = 0;//滑动的值存储
        int max = 0;//存放最多的结果
        while (left < s.length()) {
            count +=isVowel(s.charAt(left));
            left++;
            if(left>=k){
                max =Math.max(max,count);

                //窗口移动
                count -=isVowel(s.charAt(left-k));
            }

        }
        return max;
    }


    public int isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ? 1 : 0;
    }


}
