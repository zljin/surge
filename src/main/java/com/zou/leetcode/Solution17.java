package com.zou.leetcode;

import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author leonard
 * @date 2022/7/4
 * @Description https://leetcode.cn/problems/letter-combinations-of-a-phone-number/
 */
public class Solution17 {
    public static void main(String[] args) {
        System.out.println(new Solution17().letterCombinations("23"));
    }


    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();
        if (StringUtils.isEmpty(digits)) {
            return combinations;
        }
        Map<Character, List<String>> teleMap = new HashMap<Character, List<String>>() {{
            put('2', Arrays.asList("a", "b", "c"));
            put('3', Arrays.asList("d", "e", "f"));
            put('4', Arrays.asList("g", "h", "i"));
            put('5', Arrays.asList("j", "k", "l"));
            put('6', Arrays.asList("m", "n", "o"));
            put('7', Arrays.asList("p", "q", "r", "s"));
            put('8', Arrays.asList("t", "u", "v"));
            put('9', Arrays.asList("w", "x", "y", "z"));
        }};

        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < digits.length(); i++) {
            List<String> ss = teleMap.get(digits.charAt(i));
            if (queue.isEmpty()) {
                queue.addAll(ss);
            } else {
                int len = queue.size();
                for (int index = 0; index < len; index++) {
                    String poll = queue.poll();
                    for (String s : ss) {
                        queue.add(poll + s);
                    }
                }
            }
        }
        combinations.addAll(queue);
        return combinations;
    }
}
