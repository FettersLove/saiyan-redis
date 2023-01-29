package com.github.saiyan.cache.core.support.load;

import com.github.saiyan.cache.api.ICache;
import com.github.saiyan.cache.api.ICacheLoad;

/**
 * 加载策略-无
 * @author saiyan
 * @since 0.0.7
 */
public class CacheLoadNone<K,V> implements ICacheLoad<K,V> {

    @Override
    public void load(ICache<K, V> cache) {
        //nothing...
    }

}
