package com.github.saiyan.cache.core.support.evict;

import com.github.saiyan.cache.api.ICacheEntry;
import com.github.saiyan.cache.api.ICacheEvictContext;

/**
 * 丢弃策略
 * @author saiyan
 * @since 0.0.2
 */
public class CacheEvictNone<K,V> extends AbstractCacheEvict<K,V> {

    @Override
    protected ICacheEntry<K, V> doEvict(ICacheEvictContext<K, V> context) {
        return null;
    }

}
