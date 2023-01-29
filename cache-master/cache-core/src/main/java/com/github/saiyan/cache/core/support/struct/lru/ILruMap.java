package com.github.saiyan.cache.core.support.struct.lru;

import com.github.saiyan.cache.api.ICacheEntry;

/**
 * LRU map 接口
 */
public interface ILruMap<K,V> {

    /**
     * 移除最老的元素
     * @return 移除的明细
     */
    ICacheEntry<K, V> removeEldest();

    //更新 key 的信息
    void updateKey(final K key);

    //移除对应的 key 信息
    void removeKey(final K key);

    //是否为空
    boolean isEmpty();

    //是否包含元素
    boolean contains(final K key);

}
