package com.zou.algorithm.DynamicProgramming;

/**
 * @author leonard
 * @Description 动态规划钢条切割求最大收益
 * @date 2021-04-12 11:05
 */
public class SteelBarCutProblem {
    public static void main(String[] args) {
        //价钱表
        int[] p = {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        int n = 8;
        //step 1.刻画最优子结构
        //step 2.递归的定义最优解 r[n] = max{p[i]+r[n-i]} 1<=i<=n

        //step 3.计算最优解的值
        int[] r = new int[p.length];//记录最优切割价格
        //step 4:利用计算出的信息构造一个最优解，了解最优解的具体情况
        int[] s = new int[p.length];//记录最优切割长度

        r[0] = 0;
        for (int i = 1; i <= n; i++) {
            int q = Integer.MIN_VALUE;
            for (int j = 1; j <= i; j++) {
                if (q < p[j] + r[i - j]) {
                    q = p[j] + r[i - j];
                    s[i] = j;
                }
            }
            r[i] = q;
        }
        System.out.println(n + "米钢材最优收益为：" + r[n]);
        System.out.println(n + "米钢材最优切割的每一段具体长度：");
        while (n > 0) {
            System.out.print(s[n]+" ");
            n = n - s[n];
        }
    }
}
