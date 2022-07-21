package com.zou.leetcode;

import com.zou.dataobject.ListNode;

/**
 * @author leonard
 * @date 2022/6/15
 * @Description https://leetcode.cn/problems/intersection-of-two-linked-lists/
 */
public class Solution160 {
    public static void main(String[] args) {

    }

    //方法1：通过headA.next = headB 变成一个环形链表，然后判断即可

    //方法2：headA存入到Set集合中，然后递归headB去找有没有相同的即可

    //方法3：双指针
    //https://leetcode.cn/problems/intersection-of-two-linked-lists/solution/tu-jie-xiang-jiao-lian-biao-by-user7208t/
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode pA = headA;
        ListNode pB = headB;
        while (pA != pB) {
            pA = (pA == null ? headB : pA.getNext());
            pB = (pB == null ? headA : pB.getNext());
        }
        return pA;
    }
}
