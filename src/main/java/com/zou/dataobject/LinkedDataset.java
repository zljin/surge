package com.zou.dataobject;

/**
 * @author leonard
 * @date 2022/6/15
 * @Description linkedList collection
 */
public class LinkedDataset<E> implements IDataset<E> {

    private ListNode<E> dummyHead;

    private int size;

    public LinkedDataset() {
        this.dummyHead = new ListNode<>(null, null);
        this.size = 0;
    }

    //@SuppressWarnings("unchecked")
    @Override
    public void add(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException();
        ListNode<E> pre = dummyHead;
        for (int i = 0; i < index; i++) {
            // find before index of purpose
            pre = pre.getNext();
        }
        pre.setNext(new ListNode<E>(e, pre.getNext()));
        this.size++;
    }

    @Override
    public void addFirst(E e) {
        this.add(0, e);
    }

    @Override
    public void addLast(E e) {
        this.add(size, e);
    }

    @Override
    public E get(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException();
        ListNode<E> cur = dummyHead.getNext();
        for (int i = 0; i < index; i++) {
            cur = cur.getNext();
        }
        return cur.getVal();
    }

    @Override
    public ListNode<E> getNode(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException();
        ListNode<E> cur = dummyHead.getNext();
        for (int i = 0; i < index; i++) {
            cur = cur.getNext();
        }
        return cur;
    }


    @Override
    public E getFirst() {
        return this.get(0);
    }

    @Override
    public E getLast() {
        return this.get(size);
    }

    @Override
    public void set(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException();
        ListNode<E> cur = dummyHead.getNext();
        for (int i = 0; i < index; i++) {
            cur = cur.getNext();
        }
        cur.setVal(e);
    }

    @Override
    public boolean contains(E e) {
        ListNode pre = dummyHead;
        while (pre.getNext() != null) {
            pre = pre.getNext();
            if (pre.getVal() == e) return true;
        }
        return false;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException();
        ListNode<E> pre = dummyHead;
        for (int i = 0; i < index; i++) {
            pre = pre.getNext();
        }

        //clear delNode and gc
        ListNode<E> delNode = pre.getNext();
        delNode.setNext(null);

        pre.setNext(pre.getNext().getNext());
        this.size--;
        return delNode.getVal();
    }

    @Override
    public E removeElement(E e) {
        ListNode<E> pre = dummyHead;
        while (pre.getNext() != null) {
            pre = pre.getNext();
            if (pre.getVal() == e) break;
        }

        if(pre.getNext() == null) return null;

        //clear delNode and gc
        ListNode<E> delNode = pre;
        delNode.setNext(null);

        pre.setNext(pre.getNext().getNext());

        return delNode.getVal();
    }

    @Override
    public E removeLast() {
        return this.remove(size);
    }

    @Override
    public E removeFirst() {
        return this.remove(0);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("dummyHead->");
        ListNode<E> cur = dummyHead.getNext();
        while (cur != null) {
            sb.append(cur + "->");
            cur = cur.getNext();
        }
        sb.append("NULL");
        return sb.toString();
    }
}
