package com.github.saiyan.cache.core.load;

import com.github.saiyan.cache.api.ICache;
import com.github.saiyan.cache.api.ICacheLoad;


public class MyCacheLoad implements ICacheLoad<String,String> {

    @Override
    public void load(ICache<String, String> cache) {
        cache.put("1", "1");
        cache.put("2", "2");
    }

}
