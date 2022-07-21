package com.zou.leetcode;

import com.zou.dataobject.IDataset;
import com.zou.dataobject.LinkedDataset;
import com.zou.dataobject.ListNode;

/**
 * @author leonard
 * @date 2022/6/15
 * @Description https://leetcode.cn/problems/merge-two-sorted-lists/
 */
public class Solution21 {

    public static void main(String[] args) {

        IDataset<Integer> l1 = new LinkedDataset<>();
        IDataset<Integer> l2 = new LinkedDataset<>();
        l1.addLast(1);
        l1.addLast(4);
        l1.addLast(5);
        l2.addLast(2);
        l2.addLast(3);
        l2.addLast(9);

        ListNode<Integer> l3 = new Solution21().mergeTwoList(l1.getNode(0), l2.getNode(0),1);

        ListNode pre = l3;
        ListNode preFinal = new ListNode();
        while (pre.getNext() != null) {
            System.out.print(pre.getVal() + "->");
            pre = pre.getNext();
            preFinal = pre;
        }
        System.out.println(preFinal.getVal());

    }

    /**
     * @param l1 1->4->5
     * @param l2 2->3->9
     * @return
     */
    public ListNode<Integer> mergeTwoList(ListNode<Integer> l1, ListNode<Integer> l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.getVal() < l2.getVal()) {
            l1.setNext(mergeTwoList(l1.getNext(), l2));
            return l1;
        } else {
            l2.setNext(mergeTwoList(l1, l2.getNext()));
            return l2;
        }
    }

    public ListNode<Integer> mergeTwoList(ListNode<Integer> l1, ListNode<Integer> l2, int a) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode<Integer> dummyHead = new ListNode<>();

        //移动指针
        ListNode<Integer> cur = dummyHead;

        while (l1 != null && l2 != null) {

            Integer v1 = l1.getVal();
            Integer v2 = l2.getVal();
            if (v1 < v2) {
                cur.setNext(l1);
                l1 = l1.getNext();
            } else {
                cur.setNext(l2);
                l2 = l2.getNext();
            }
            cur = cur.getNext();
        }

        if (l1 == null) {
            cur.setNext(l2);
        } else if (l2 == null) {
            cur.setNext(l1);
        }

        return dummyHead.getNext();
    }


}
