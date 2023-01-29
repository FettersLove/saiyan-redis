package com.github.saiyan.cache.api;

/**
 * 缓存接口
 */
public interface ICacheLoad<K, V> {

    //加载缓存信息
    void load(final ICache<K,V> cache);

}
