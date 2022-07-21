package com.zou.leetcode;

import com.zou.dataobject.IDataset;
import com.zou.dataobject.LinkedDataset;
import com.zou.dataobject.ListNode;
import com.zou.utils.ZUtil;

/**
 * @author leonard
 * @date 2022/7/7
 * @Description https://leetcode.cn/problems/remove-nth-node-from-end-of-list/
 */
public class Solution19 {
    public static void main(String[] args) {
        IDataset<Integer> l1 = new LinkedDataset<>();
        l1.addLast(1);
        l1.addLast(2);
        l1.addLast(3);
        l1.addLast(4);
        l1.addLast(5);
        ListNode head = l1.getNode(0);
        ZUtil.printListNodes(head);
        new Solution19().removeNthFromEnd(head, 2);
        ZUtil.printListNodes(head);

    }

    /**
     * 双指针：滑动窗口算法
     * <p>
     * 1. 定义slow和fast指针，长度为n
     * 2. 当fast移动到null时，slow就移动到待删除的节点的前一个节点
     * 3. slow 和 fast初始节点在dummyhead
     *
     * @param head
     * @param n    窗口大小
     */
    private ListNode removeNthFromEnd(ListNode<Integer> head, int n) {
        ListNode<Integer> dummyHead = new ListNode<>(0, head);
        dummyHead.setNext(head);
        ListNode slow = dummyHead;
        ListNode fast = dummyHead;
        for (int i = 0; i <= n; i++) {//这边的边界要注意是n+1,可以注意是找到待删除的前一个点
            fast = fast.getNext();
        }

        while (fast != null) {
            slow = slow.getNext();
            fast = fast.getNext();
        }

        ListNode delNode = slow.getNext();
        slow.setNext(delNode.getNext());
        delNode.setNext(null);
        return dummyHead.getNext();
    }
}
