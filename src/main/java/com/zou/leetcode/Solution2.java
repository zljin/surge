package com.zou.leetcode;

import java.io.IOException;

/**
 * @author leonard
 * @date 2022/6/10
 * @Description 链表
 *
https://leetcode.cn/problems/add-two-numbers/
 */
public class Solution2 {

    public static void main(String[] args) throws IOException {
        String line1Str = "[2,4,3]";
        String line2Str = "[5,6,4]";
        ListNode l1 = stringToListNode(line1Str);
        ListNode l2 = stringToListNode(line2Str);
        ListNode ret = addTwoNumbers(l1, l2);
        String out = listNodeToString(ret);
        System.out.print(out);
        //out:[7,0,8]
    }


    /**
     * 逆序
     * @param l1 "[2,4,3]" 342
     * @param l2 "[5,6,4]" 465
     * @return
     */
    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        /**
         * 链表题必先定义prev和cur滚动指针
         */
        ListNode prev = new ListNode();
        int carry = 0;
        ListNode cur = prev;

        while (l1 != null || l2 != null) {

            int x = l1!=null?l1.val:0;
            int y = l2!=null?l2.val:0;

            int sum = x+y+carry;
            carry = sum/10;
            sum = sum%10;
            cur.next = new ListNode(sum);
            cur = cur.next;

            if(l1!=null){
                l1 = l1.next;
            }

            if(l2!=null){
                l2 = l2.next;
            }
        }
        if(carry==1){
            cur.next = new ListNode((carry));
        }
        return prev.next;
    }


    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for (int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static ListNode stringToListNode(String input) {
        // Generate array from the input
        int[] nodeValues = stringToIntegerArray(input);

        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for (int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }

    public static String listNodeToString(ListNode node) {
        if (node == null) {
            return "[]";
        }

        String result = "";
        while (node != null) {
            result += Integer.toString(node.val) + ", ";
            node = node.next;
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }


}

