package com.github.saiyan.cache.core.cmd;

import java.util.concurrent.ConcurrentHashMap;

public class KvStore {

    private static final ConcurrentHashMap<String,String> hashMap = new ConcurrentHashMap<>();

    public static void set(String k,String v){
        hashMap.put(k, v);
    }

    public static String get(String k){
        return hashMap.get(k);
    }

}


