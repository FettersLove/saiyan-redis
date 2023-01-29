package com.github.saiyan.cache.api;

/**
 * 删除监听器上下文
 *
 * （1）耗时统计
 * （2）监听器
 */
public interface ICacheRemoveListenerContext<K,V> {

    //清空的 key
    K key();

    //值
    V value();

    //删除类型
    String type();

}
