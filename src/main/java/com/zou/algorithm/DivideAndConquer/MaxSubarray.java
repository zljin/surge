package com.zou.algorithm.DivideAndConquer;

/**
 * @Description: 最大子数组
 * @version: v1.1.0
 * @author: zoulj
 * @date: 2021-04-20 22:29:08
 *
 * 动态规划和分治法的区别：
 * 1. 分治法将问题划分成一些独立地子问题，递归地求解各子问题，然后合并子问题的解而得到原问题的解
 *
 * 2. 动态规划适用于子问题独立且重叠的情况，也就是各子问题包含公共的子问题,
 * 若用分治法则会做许多不必要的工作，即重复地求解公共的子问题。动态规划算法对每个子子问题只求解一次，
 * 将其结果保存在一张表中，从而避免不必要的重复计算
 *
 * 3. 分治法：各子问题独立   动态规划：各子问题重叠
 *
 * 4. 适合动态规划的最优化问题中的两个要素：最优子结构和重叠子问题
 *  最优子结构：如果问题的一个最优解中包含了子问题的最优解，则该问题具有最优子结构。
 *  重叠子问题：子问题的空间要很小，用来求解原问题的递归算法反复地解同样的子问题，而不是总在产生新的子问题。
 * 对两个子问题来说，如果它们确实是相同的子问题，只是作为不同问题的子问题出现的话，则它们是重叠的。
 *
 *
 * 分治法：各个子问题递归独立求解,然后合并子问题
 * 动态规划：各个子问题重叠求解
 *
 * 这两个方法都要找到最优子结构
 *
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * -------------------------------------------------------------
 * 2021-04-20 22:29:08      zoulj          v1.1.0               修改原因
 */
public class MaxSubarray {
    //动态规划
    public static int maxSubArray(int[] A) {
        //f(i) 代表以第 i个数结尾的「连续子数组的最大和」
        //f(i)=max{f(i−1)+A[i],A[i]}
        //f(1)=max{f(0)+A[1],A[1]}
        //f(0)=max{f(-1)+A[0],A[0]}
        //max = max{f0....fn}
        int f = 0, maxResult = A[0];
        int[] F = new int[A.length];
        int i = 0;
        for (int a : A) {
            f = Math.max(a, f + a);
            F[i++] = f;
            maxResult = Math.max(f, maxResult);
        }

        for (int j = 0; j < F.length - 1; j++) {
            System.out.println("F["+j+"]:"+F[j]);
        }
        return maxResult;
    }

    //分治法
    public static Result FindMaxSubarray(int[] A, int low, int high) {
        if (low == high) return new Result(low, high, A[low]);

        int mid = (low + high) / 2;

        //递归拆分：三种可能,最大子数组在左边、右边、交叉
        Result left = FindMaxSubarray(A, low, mid);
        Result right = FindMaxSubarray(A, mid + 1, high);
        Result cross = FindMaxCrossSubarray(A, low, mid, high);

        if (cross.sum > right.sum && cross.sum > left.sum) {
            return cross;
        }
        if (right.sum > cross.sum && right.sum > left.sum) {
            return right;
        }
        if (left.sum > cross.sum && left.sum > right.sum) {
            return left;
        }
        return new Result(low, high, 0);
    }

    private static Result FindMaxCrossSubarray(int[] A, int low, int mid, int high) {
        int leftSum = Integer.MIN_VALUE;//左边的最大值
        int maxleft = mid;//左边最大的偏移量
        int sum = 0;
        for (int i = mid; i >= low; i--) {
            sum += A[i];
            if (leftSum < sum) {
                leftSum = sum;
                maxleft = i;
            }
        }

        int rightSum = Integer.MIN_VALUE;
        int maxright = mid + 1;
        sum = 0;
        for (int i = mid + 1; i <= high; i++) {
            sum += A[i];
            if (rightSum < sum) {
                rightSum = sum;
                maxright = i;
            }
        }

        //合并
        return new Result(maxleft, maxright, leftSum + rightSum);
    }


    //内部类，用来存储最大子数组的返回结果，
    private static class Result {
        int low;
        int high;
        int sum;

        public Result(int low, int high, int sum) {
            this.low = low;
            this.high = high;
            this.sum = sum;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "low=" + low +
                    ", high=" + high +
                    ", sum=" + sum +
                    '}';
        }
    }


    public static void main(String[] args) {
        int[] A = {12, -3, -16, 20, -19, -3, 18, 20, -7, 12, -9, 7, -10};
        System.out.println(FindMaxSubarray(A, 0, A.length - 1));
        System.out.println(maxSubArray(A));
    }
}
