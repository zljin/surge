package com.zou.algorithm.TwoPoint;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20%E9%A2%98%E8%A7%A3%20-%20%E5%8F%8C%E6%8C%87%E9%92%88.md
 * 双指针练习题目
 */

public class MainClass {

    static class Node{
        Node next;
        String val;

        public Node(Node next, String val) {
            this.next = next;
            this.val = val;
        }

        public Node() {
        }
    }


    public static void main(String[] args) {

        //main1();
        //main2();
        //main3();
        //main4();
        //main5();
        //main6();
        main7();
    }


    public static void main7(){
        String s = "abpcplea";
        List<String> list = Arrays.asList("ale", "apple", "monkey", "plea");
        System.out.println(longestLine(s,list));
    }

    public static String longestLine(String s,List<String> list){
        String re = "";

        for(String a:list){

            if(re.length()>a.length()||re.length()==a.length()&&re.compareTo(a)<0){
                continue;
            }

            if(isSubStr(s,a)){
                re = a;
            }
        }

        return re;
    }

    private static boolean isSubStr(String s, String a) {

        int i = 0;
        int j = 0;
        while(i<s.length()&&j<a.length()){
            if(s.charAt(i) == a.charAt(j)){
                j++;
            }
            i++;
        }
        return a.length()==j;
    }

    public static void main6(){
        Node node = new Node();
        System.out.println(hasCycle(node));
    }

    private static boolean hasCycle(Node head) {
        if(head == null){
            return false;
        }

        Node i = head,j = head.next;
        while(i!=null&&j!=null&&j.next!=null){
            if(i==j){
                return true;
            }
            i = i.next;
            j = j.next.next;
        }

        return false;
    }

    public static void main5(){
        int[]nums1 = {1,2,3,0,0,0};
        int[]nums2 = {2,5,6};
        merge(nums1,3,nums2,3);
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m-1;
        int p2 = n-1;
        int p = m+n-1;

        while((p1>=0)&&(p2>=0)){
            nums1[p--] = (nums1[p1]<nums2[p2]) ? nums2[p2--]:nums1[p1--];
        }
    }

    public static void main4(){
        System.out.println(ishuiwendeleteOne("abca"));
    }

    @SuppressWarnings("unchecked")
    public static Boolean ishuiwendeleteOne(String s){
        char[] charArray = s.toCharArray();
        for(int i = 0,j=s.length()-1;i<j;i++,j--){
            if(charArray[i] != charArray[j]){
                return ishuiwens(charArray,i,j-1)||ishuiwens(charArray,i+1,j);
            }
        }

        return false;

    }

    private static boolean ishuiwens(char[] charArray, int i, int j) {
        while(j>i){
            if(charArray[j]!=charArray[i]){
                return false;
            }
            j--;
            i++;
        }
        return true;
    }

    public static void main3(){
        String s = "leetcode";
        System.out.println(reversevowelStr(s));
    }

    private static String reversevowelStr(String s) {
        if(StringUtils.isEmpty(s)){
            return null;
        }
        Set vowelSets = new HashSet(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

        char[] charArray = s.toCharArray();
        int i = 0,j = s.length()-1;
        while(j>i){
            if(vowelSets.contains(charArray[i])&&vowelSets.contains(charArray[j])){
                char temp = charArray[i];
                charArray[i] = charArray[j];
                charArray[j] = temp;
            }
            i++;
            j--;
        }
        return String.valueOf(charArray);
    }


    public static void main2(){
        int target = 5;
        System.out.println(isPingfangSum(target));
    }

    public static boolean isPingfangSum(int target){
        if(target<0){
            return false;
        }
        int i = 0;
        int j = (int) Math.sqrt(target);
        while(j>i){
            int result = i*i+j*j;
            if(target == result){
                System.out.println("i:"+i+" j:"+j);
                return true;
            }

            else if(target>result){
                i++;
            }else {
                j--;
            }

        }
        return false;
    }


    public static void main1(){
        int[] arr = new int[]{2,7,11,15};
        int target = 9;

        int[] ints = returnTwoSumIndex(arr, target);
        for(int i:ints){
            System.out.println(i);
        }
    }

    public static int[] returnTwoSumIndex(int[] arr,int target){

        if(arr == null){
            return null;
        }

        if(arr.length==0){
            return null;
        }

        int i = 0;
        int j = arr.length-1;

        while(j>i){
            if(target == arr[i]+arr[j]){
                return new int[]{i,j};
            }else if(target>arr[i]+arr[j]){
                i++;
            }else{
                j--;
            }
        }
        return null;
    }
}
