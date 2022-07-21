package com.zou.dataobject;

/**
 *
 * @author leonard
 * @date 2022/6/15
 * @Description map interface
 */
public interface Map<K, V> {

    void add(K key, V value);
    V remove(K key);
    boolean contains(K key);
    V get(K key);
    void set(K key, V newValue);
    int getSize();
    boolean isEmpty();
}
