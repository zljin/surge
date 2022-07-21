package com.zou.dataobject;

/**
 * @author leonard
 * @date 2022/6/15
 * @Description 树节点
 */
public class TreeNode<E> {

    private E val;

    private TreeNode<E> left,right;

    public TreeNode(E val) {
        this.val = val;
        this.setLeft(null);
        this.setRight(null);
    }

    public E getVal() {
        return val;
    }

    public void setVal(E val) {
        this.val = val;
    }

    public TreeNode<E> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<E> left) {
        this.left = left;
    }

    public TreeNode<E> getRight() {
        return right;
    }

    public void setRight(TreeNode<E> right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
