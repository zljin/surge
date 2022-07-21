package com.zou.leetcode;

import com.zou.dataobject.TreeNode;

import java.util.LinkedList;

/**
 * @author leonard
 * @date 2022/6/15
 * @Description https://leetcode.cn/problems/maximum-depth-of-binary-tree/
 */
public class Solution104 {

    public static void main(String[] args) {

    }

    //dfs
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.getLeft() == null && root.getRight() == null) {
            return 1;
        }

        return Math.max(maxDepth(root.getLeft()), maxDepth(root.getRight())) + 1;
    }

    //bfs
    public int maxDepth(TreeNode root, int a) {
        if (root == null) {
            return 0;
        }
        if (root.getLeft() == null && root.getRight() == null) {
            return 1;
        }
        int result = 0;

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.addFirst(root.getLeft());
        queue.addFirst(root.getRight());

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.removeLast();
                if (treeNode.getLeft() != null) {
                    queue.addFirst(treeNode.getLeft());
                }
                if (treeNode.getRight() != null) {
                    queue.addFirst(treeNode.getRight());
                }
                result++;
            }
        }
        return result;
    }
}
