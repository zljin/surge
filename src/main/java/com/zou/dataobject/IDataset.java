package com.zou.dataobject;

/**
 * @author leonard
 * @date 2022/6/15
 * @Description list interface define
 */
public interface IDataset<E> {

    void add(int index, E e);

    void addFirst(E e);

    void addLast(E e);

    E get(int index);

    ListNode<E> getNode(int index);

    E getFirst();

    E getLast();

    void set(int index, E e);

    boolean contains(E e);

    E remove(int index);

    E removeElement(E e);

    E removeLast();

    E removeFirst();

    int size();

    boolean isEmpty();

}
