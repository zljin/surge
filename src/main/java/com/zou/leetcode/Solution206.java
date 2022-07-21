package com.zou.leetcode;

import com.zou.dataobject.IDataset;
import com.zou.dataobject.LinkedDataset;
import com.zou.dataobject.ListNode;
import com.zou.utils.ZUtil;

import java.util.Stack;

/**
 * @author leonard
 * @date 2022/6/16
 * @Description https://leetcode.cn/problems/reverse-linked-list/comments/
 */
public class Solution206 {

    public static void main(String[] args) {
        IDataset<Integer> l1 = new LinkedDataset<>();
        l1.addLast(1);
        l1.addLast(4);
        l1.addLast(5);
        //1->4->5
//        ListNode listNode = new Solution206().reverseList(l1.getNode(0));
//        ZUtil.printListNodes(listNode);

//        ListNode listNode2 = new Solution206().reverseList(l1.getNode(0),1);
//        ZUtil.printListNodes(listNode2);
        ListNode listNode3 = new Solution206().reverseListRecrusion(l1.getNode(0));
        ZUtil.printListNodes(listNode3);
    }

    //递归有点难理解
    public ListNode reverseListRecrusion(ListNode head) {
        if (head == null || head.getNext() == null) {
            return head;
        }
        ListNode p = reverseListRecrusion(head.getNext());
        head.getNext().setNext(head);
        head.setNext(null);
        return p;
    }

    //方法1：最先想到用栈
    public ListNode reverseList(ListNode head) {

        ListNode head2 = new ListNode();
        Stack<ListNode> stack = new Stack<>();
        while(head!=null){
            stack.push(head);
            head = head.getNext();
        }

        ListNode cur = head2;
        while(!stack.isEmpty()){
            ListNode pop = stack.pop();
            cur.setNext(null);
            cur.setNext(pop);
            cur = cur.getNext();
        }
        cur.setNext(null);
        return head2.getNext();
    }

    //方法2：用迭代
    public ListNode reverseList(ListNode head,int a){
        ListNode pre = null;
        ListNode cur = head;
        while(cur!=null){
            ListNode nextTemp = cur.getNext();
            cur.setNext(pre);
            pre = cur;
            cur = nextTemp;
        }
        return pre;
    }

    //递归




}
