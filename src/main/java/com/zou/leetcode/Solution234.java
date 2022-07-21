package com.zou.leetcode;

import com.zou.dataobject.IDataset;
import com.zou.dataobject.LinkedDataset;
import com.zou.dataobject.ListNode;
import com.zou.utils.ZUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leonard
 * @date 2022/6/16
 * @Description https://leetcode.cn/problems/palindrome-linked-list/
 */
public class Solution234 {
    public static void main(String[] args) {
        IDataset<Integer> l1 = new LinkedDataset<>();
        l1.addLast(1);
        l1.addLast(2);
        l1.addLast(2);
        l1.addLast(1);
        ZUtil.printListNodes(l1.getNode(0));
        System.out.println(new Solution234().isPalindrome(l1.getNode(0)));

    }

    public boolean isPalindrome(ListNode<Integer> head) {
        List<Integer> vals = new ArrayList<Integer>();

        // 将链表的值复制到数组中
        ListNode<Integer> currentNode = head;
        while (currentNode != null) {
            vals.add(currentNode.getVal());
            currentNode = currentNode.getNext();
        }

        // 使用双指针判断是否回文
        int front = 0;
        int back = vals.size() - 1;
        while (front < back) {
            if (!vals.get(front).equals(vals.get(back))) {
                return false;
            }
            front++;
            back--;
        }
        return true;
    }
}
