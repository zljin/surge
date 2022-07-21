package com.zou.algorithm.GreedyAlgorithm;

import java.util.Arrays;

/**
 * 贪心算法：
 *      优先满足最小的条件，通过局部最优解达到全局最优解
 *      找到其最优的子结构
 *
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20%E9%A2%98%E8%A7%A3%20-%20%E8%B4%AA%E5%BF%83%E6%80%9D%E6%83%B3.md
 *
 */
public class MainClass {

    public static void main(String[] args) {
        //main1();
        //main10();
        //main5();
        main6();
    }

    public static void main6(){
        int[] stocks = {7,1,5,3,6,4};
        System.out.println(maxProfit2(stocks));
    }

    private static int maxProfit2(int[] stocks) {
        if(stocks==null||stocks.length==0) return 0;
        int r = 0;
        for(int i = 1;i<stocks.length;i++){

            if(stocks[i]>stocks[i-1]){
                r += (stocks[i]-stocks[i-1]);
            }
        }
        return r;
    }

    public static void main5(){
        int[] stocks = {7,1,5,3,6,4};
        System.out.println(maxProfit(stocks));
    }

    private static int maxProfit(int[] stocks) {

        if(stocks==null||stocks.length==0) return 0;

        int s = stocks[0];
        int r = 0;
        for(int i = 1;i<stocks.length;i++){

            if(s>stocks[i])
                s = stocks[i];
            else
                r = Math.max(r,stocks[i]-s);
        }
        return r;
    }

    public static void main10(){
        //子数组最大和
        int[] nums = new int[]{-2,1,-3,4,-1,2,1,-5,4};

        System.out.println(maxSubArray(nums));


    }

    private static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int s = nums[0];
        int r = s;

        for (int i = 0; i < nums.length; i++) {
            s = s > 0 ? s + nums[i] : nums[i];
            r = Math.max(r, s);
        }
        return r;
    }


    public static void main1() {
        int[] grid = new int[]{1,4};
        int[] size = new int[]{1,2,4};
        System.out.println(findContentChild(grid,size));
    }

    private static int findContentChild(int[] grid, int[] size) {
        if(grid==null||size==null) return 0;
        Arrays.sort(grid);
        Arrays.sort(size);

        int g1=0,s1=0;
        while(g1<grid.length && s1<size.length){
            if(grid[g1]<size[s1]){
                g1++;
            }
            s1++;
        }
        return g1;
    }


}
