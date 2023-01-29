package com.github.saiyan.cache.core.skipList;

/**
 * Author: 王俊超
 * Date: 2018-01-15 09:46
 * Blog: http://blog.csdn.net/derrantcm
 * Github: https://github.com/wang-jun-chao
 * All Rights Reserved !!!
 */
public class SkipListEntry<K,V> {

    // data
    public K key;
    public V value;

    // links
    public SkipListEntry up;
    public SkipListEntry down;
    public SkipListEntry left;
    public SkipListEntry right;

    // special
    public static final String negInf = "-oo";
    public static final String posInf = "+oo";

    // constructor
    public SkipListEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    // methods...
}