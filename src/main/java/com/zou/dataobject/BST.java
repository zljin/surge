package com.zou.dataobject;

import java.util.Stack;

/**
 * @author leonard
 * @date 2022/6/19
 * @Description 二叉搜索树
 *
 * 重点回顾下三个方法
 * add
 * remove
 * inorder
 *
 */
public class BST<E extends Comparable<E>> {

    private TreeNode<E> root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public void add(E e) {
        this.root = add(root, e);
    }

    private TreeNode<E> add(TreeNode<E> node, E e) {
        if (node == null) {
            size++;
            return new TreeNode<E>(e);
        }

        if (e.compareTo(node.getVal()) < 0) {
            node.setLeft(add(node.getLeft(), e));
        } else {
            node.setRight(add(node.getRight(), e));
        }
        return node;
    }


    // 从二分搜索树中删除元素为e的节点
    public void remove(E e) {
        root = remove(root, e);
    }

    // 删除掉以node为根的二分搜索树中值为e的节点, 递归算法
    // 返回删除节点后新的二分搜索树的根
    private TreeNode<E> remove(TreeNode<E> node, E e) {

        if (node == null)
            return null;

        if (e.compareTo(node.getVal()) < 0) {
            node.setLeft(remove(node.getLeft(), e));
            return node;
        } else if (e.compareTo(node.getVal()) > 0) {
            node.setRight(remove(node.getRight(), e));
            return node;
        } else {   // e.compareTo(node.e) == 0

            // 待删除节点左子树为空的情况
            if (node.getLeft() == null) {
                TreeNode<E> rightNode = node.getRight();
                node.setRight(null);
                size--;
                return rightNode;
            }

            // 待删除节点右子树为空的情况
            if (node.getRight() == null) {
                TreeNode<E> leftNode = node.getLeft();
                node.setLeft(null);
                size--;
                return leftNode;
            }

            // 待删除节点左右子树均不为空的情况

            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点的位置
            TreeNode<E> successor = minimum(node.getRight());
            successor.setRight(removeMin(node.getRight()));
            successor.setLeft(node.getLeft());

            node.setLeft(null);
            node.setRight(null);
            return successor;
        }
    }


    // 从二分搜索树中删除最小值所在节点, 返回最小值
    public E removeMin() {
        E ret = minimum();
        root = removeMin(root);
        return ret;
    }

    // 删除掉以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private TreeNode<E> removeMin(TreeNode<E> node) {

        if (node.getLeft() == null) {
            TreeNode<E> rightNode = node.getRight();
            node.setRight(null);
            size--;
            return rightNode;
        }

        node.setLeft(removeMin(node.getLeft()));
        return node;
    }


    // 寻找二分搜索树的最小元素
    public E minimum() {
        if (size == 0)
            throw new IllegalArgumentException("BST is empty!");

        return minimum(root).getVal();
    }

    // 返回以node为根的二分搜索树的最小值所在的节点
    private TreeNode<E> minimum(TreeNode<E> node) {
        if (node.getLeft() == null)
            return node;
        return minimum(node.getLeft());
    }


    public void inOrder() {
        this.inOrder(this.root);
    }

    private void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.getLeft());
        System.out.println(node.getVal());
        inOrder(node.getRight());
    }

    public void inOrderNR() {
        Stack<TreeNode<E>> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode<E> cur = stack.pop();
            if (cur.getRight() != null)
                stack.push(cur.getRight());
            System.out.println(cur.getVal());
            if (cur.getLeft() != null)
                stack.push(cur.getLeft());
        }
    }


    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    // 生成以node为根节点，深度为depth的描述二叉树的字符串
    private void generateBSTString(TreeNode<E> node, int depth, StringBuilder res) {

        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }

        res.append(generateDepthString(depth) + node.getVal() + "\n");
        generateBSTString(node.getLeft(), depth + 1, res);
        generateBSTString(node.getRight(), depth + 1, res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++)
            res.append("--");
        return res.toString();
    }
}
