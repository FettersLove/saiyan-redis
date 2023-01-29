package com.github.saiyan.cache.core.support.map;

import java.util.HashMap;
import java.util.Map;


public final class Maps {

    private Maps(){}

    /**
     * hashMap 实现策略
     * @param <K> key
     * @param <V> value
     */
    public static <K,V> Map<K,V> hashMap() {
        return new HashMap<>();
    }

}
