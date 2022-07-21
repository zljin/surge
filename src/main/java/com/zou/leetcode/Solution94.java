package com.zou.leetcode;

import com.zou.dataobject.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author leonard
 * @date 2022/6/15
 * @Description https://leetcode.cn/problems/binary-tree-inorder-traversal/
 */
public class Solution94 {
    public static void main(String[] args) {

    }

    public List<Integer> inorderTraversal(TreeNode<Integer> root) {
        List<Integer> result = new ArrayList<>();
        inorder(root, result);
        return result;
    }

    private void inorder(TreeNode<Integer> root, List<Integer> result) {
        if (root == null) {
            return;
        }
        inorder(root.getLeft(), result);
        result.add(root.getVal());
        inorder(root.getRight(), result);
    }

    public List<Integer> inorderTraversal(TreeNode<Integer> root, int a) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode<Integer>> deque = new LinkedList<>();

        while (root != null || !deque.isEmpty()) {
            while(root!=null){
                deque.push(root);
                root = root.getLeft();
            }

            TreeNode<Integer> node = deque.poll();
            if (node != null) {
                result.add(node.getVal());
                deque.push(node.getRight());
            }
        }


        return result;
    }


}
