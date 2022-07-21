package com.zou.dataobject;

/**
 * @author leonard
 * @date 2022/6/15
 * @Description 链表节点
 */
public class ListNode<E> {

    private E val;

    private ListNode<E> next;

    public ListNode() {
    }

    public ListNode(E val, ListNode<E> next) {
        this.val = val;
        this.next = next;
    }

    public E getVal() {
        return val;
    }

    public void setVal(E val) {
        this.val = val;
    }

    public ListNode<E> getNext() {
        return next;
    }

    public void setNext(ListNode<E> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "ListNode{ " + "val=" + val + " }";
    }
}
