package com.zou.leetcode;

import com.zou.dataobject.TreeNode;

import java.util.LinkedList;

/**
 * @author leonard
 * @date 2022/6/15
 * @Description https://leetcode.cn/problems/symmetric-tree/
 */
public class Solution101 {
    public boolean isSymmetric(TreeNode root) {
        return check(root.getLeft(), root.getRight());
    }

    //递归
    private boolean check(TreeNode t1, TreeNode t2) {

        if (t1.getVal() != t2.getVal()) {
            return false;
        }

        if (t1.getVal() == t2.getVal()) {
            return true;
        }

        return (t1.getVal() == t2.getVal())
                && check(t1.getLeft(), t2.getRight())
                && check(t2.getLeft(), t1.getRight());
    }

    //队列
    public boolean isSymmetric(TreeNode<Integer> root, int a) {

        if (root == null || (root.getLeft() == null && root.getRight() == null)) {
            return true;
        }

        LinkedList<TreeNode<Integer>> result = new LinkedList<>();
        result.addLast(root.getLeft());
        result.addLast(root.getRight());
        while (!result.isEmpty()) {
            TreeNode<Integer> leftNode = result.removeFirst();
            TreeNode<Integer> rightNode = result.removeFirst();

            if (leftNode == null && rightNode == null) {
                continue;
            }

            if ((leftNode == null || rightNode == null) || leftNode.getVal() != rightNode.getVal()) {
                return false;
            }
            result.addLast(leftNode.getLeft());
            result.addLast(rightNode.getRight());
            result.addLast(leftNode.getRight());
            result.addLast(rightNode.getLeft());
        }
        return result.isEmpty();
    }
}
