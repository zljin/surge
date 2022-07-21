package com.zou.algorithm.Recursion;

/**
 * 递归三要素：
 * 1. 递归终止条件；
 * 2. 递归终止时候的处理方法；
 * 3. 递归中重复的逻辑提取，缩小问题规模。
 * <p>
 * recursion(big_problem){
 * if (end_condition){  //满足递归的终止条件
 * solve_end_condition;  //处理终止条件下的逻辑
 * end;  //递归结束
 * }else {
 * recursion(small_problem);  //递归中重复的逻辑提取，缩小问题规模
 * }
 * }
 *
 * 把递归当成不同的方法调用,只不过此方法是自己而已
 */
public class MainClass {
    public static void main(String[] args) {
        for (int i = 1; i < 10; i++) {
            System.out.println("i:" + fibonacci(i));
        }
    }

    //斐波那契数列数列的计算 F(1)=1，F(2)=1, F(n)=F(n - 1)+F(n - 2)（n ≥ 3，n ∈ N*）
    private static int fibonacci(int n) {
        //如果是终止条件，按照要求返回终止条件对应结果
        if (n == 1 || n == 2) {
            return 1;
        } else {
            //非终止条件，按照要求把大的问题拆分成小问题，调用自身函数递归处理
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
}
