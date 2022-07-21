package com.zou.leetcode;

import com.zou.dataobject.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author leonard
 * @date 2022/6/15
 * @Description https://leetcode.cn/problems/linked-list-cycle/
 */
public class Solution141 {
    public static void main(String[] args) {

    }

    public boolean hasCycle(ListNode head) {

        if (head == null || head.getNext() == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.getNext();
        while (fast!=null) {
            if(fast.getNext()==null){
                return false;
            }
            slow = slow.getNext();
            fast = fast.getNext().getNext();
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    //set相同的元素插入会失败
    public boolean hasCycle(ListNode head,int a){
        Set<ListNode> set = new HashSet<>();
        ListNode cur = head;
        while(cur.getNext()!=null){
            boolean add = set.add(cur);
            if(!add){
                return true;
            }
            cur = cur.getNext();
        }
        return false;
    }
}
