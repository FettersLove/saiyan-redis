package com.github.saiyan.cache.core.core;

import com.github.saiyan.cache.annotation.CacheInterceptor;
import com.github.houbb.cache.api.*;
import com.github.saiyan.cache.api.*;
import com.github.saiyan.cache.core.constant.enums.CacheRemoveType;
import com.github.saiyan.cache.core.exception.CacheRuntimeException;
import com.github.saiyan.cache.core.skipList.SkipList;
import com.github.saiyan.cache.core.support.evict.CacheEvictContext;
import com.github.saiyan.cache.core.support.expire.CacheExpire;
import com.github.saiyan.cache.core.support.listener.remove.CacheRemoveListenerContext;
import com.github.saiyan.cache.core.support.persist.InnerCachePersist;
import com.github.saiyan.cache.core.support.proxy.CacheProxy;
import com.github.houbb.heaven.util.lang.ObjectUtil;

import java.util.*;

/**
 * 缓存信息
 */
public class Cache<K,V> implements ICache<K,V> {
    private SkipList skipList = new SkipList();

   //1.map信息
    private Map<K,V> map;

    //1.大小限制
    private int sizeLimit;

    //1.驱除策略
    private ICacheEvict<K,V> evict;

    //2.过期策略
    private ICacheExpire<K,V> expire;

    //3.加载类
    private ICacheLoad<K,V> load;
    //3.持久化
    private ICachePersist<K,V> persist;

    //4.删除监听类
    private List<ICacheRemoveListener<K,V>> removeListeners;
    //4.慢日志监听类
    private List<ICacheSlowListener> slowListeners;



    //1.设置 map 实现
    public Cache<K, V> map(Map<K, V> map) {
        this.map = map;
        return this;
    }

    public void setExpire(ICacheExpire<K, V> expire) {
        this.expire = expire;
    }

    //1.设置大小限制
    public Cache<K, V> sizeLimit(int sizeLimit) {
        this.sizeLimit = sizeLimit;
        return this;
    }
    //1.设置驱除策略
    public Cache<K, V> evict(ICacheEvict<K, V> cacheEvict) {
        this.evict = cacheEvict;
        return this;
    }
    //1.获取驱除策略
    @Override
    public ICacheEvict<K, V> evict() {
        return this.evict;
    }
    //2.设置过期时间
    @Override
    @CacheInterceptor
    public ICache<K, V> expire(K key, long timeInMills) {
        long expireTime = System.currentTimeMillis() + timeInMills;

        // 使用代理调用
        Cache<K,V> cachePoxy = (Cache<K, V>) CacheProxy.getProxy(this);
        return cachePoxy.expireAt(key, expireTime);
    }

    //2.指定过期信息
    @Override
    @CacheInterceptor(aof = true)
    public ICache<K, V> expireAt(K key, long timeInMills) {
        this.expire.expire(key, timeInMills);
        return this;
    }
    //2.返回过期类
    @Override
    @CacheInterceptor
    public ICacheExpire<K, V> expire() {
        return this.expire;
    }

    //3.获取持久化类
    @Override
    public ICachePersist<K, V> persist() {
        return persist;
    }

    //3.设置持久化策略
    public void persist(ICachePersist<K, V> persist) {
        this.persist = persist;
    }

    //3.返回load加载类
    @Override
    public ICacheLoad<K, V> load() {
        return load;
    }

    //3设置load加载类
    public Cache<K, V> load(ICacheLoad<K, V> load) {
        this.load = load;
        return this;
    }
    //4.返回删除监听器类
    @Override
    public List<ICacheRemoveListener<K, V>> removeListeners() {
        return removeListeners;
    }
    //4. 设置监听器类
    public Cache<K, V> removeListeners(List<ICacheRemoveListener<K, V>> removeListeners) {
        this.removeListeners = removeListeners;
        return this;
    }


    @Override
    public List<ICacheSlowListener> slowListeners() {
        return slowListeners;
    }

    public Cache<K, V> slowListeners(List<ICacheSlowListener> slowListeners) {
        this.slowListeners = slowListeners;
        return this;
    }



    /**
     * 初始化
     */
    public void init() {
        //2.设置过期机制
        this.expire = new CacheExpire<>(this);
        //3.
        this.load.load(this);

        //3.初始化持久化
        if(this.persist != null) {
            new InnerCachePersist<>(this, persist);
        }
    }


    @CacheInterceptor(refresh = true)
    public SkipList<K,V> getSkipList() {
        return skipList;
    }

    @Override
    @CacheInterceptor(refresh = true)
    public int size() {
        return map.size();
    }

    @Override
    @CacheInterceptor(refresh = true)
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    @CacheInterceptor(refresh = true, evict = true)
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    @CacheInterceptor(refresh = true)
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    @CacheInterceptor(evict = true)
    @SuppressWarnings("unchecked")
    public V get(Object key) {
        //1. 刷新所有过期信息
        K genericKey = (K) key;
        this.expire.refreshExpire(Collections.singletonList(genericKey));
        skipList.get(key);
        return map.get(key);
    }

    @Override
    @CacheInterceptor(aof = true, evict = true)
    public V put(K key, V value) {
        //1.1 尝试驱除
        CacheEvictContext<K,V> context = new CacheEvictContext<>();
        context.key(key).size(sizeLimit).cache(this);

        ICacheEntry<K,V> evictEntry = evict.evict(context);

        // 添加拦截器调用
        if(ObjectUtil.isNotNull(evictEntry)) {
            // 执行淘汰监听器
            ICacheRemoveListenerContext<K,V> removeListenerContext = CacheRemoveListenerContext.<K,V>newInstance().key(evictEntry.key())
                    .value(evictEntry.value())
                    .type(CacheRemoveType.EVICT.code());
            for(ICacheRemoveListener<K,V> listener : context.cache().removeListeners()) {
                listener.listen(removeListenerContext);
            }
        }

        //2. 判断驱除后的信息
        if(isSizeLimit()) {
            throw new CacheRuntimeException("当前队列已满，数据添加失败！");
        }

        //3. 执行添加
        skipList.put(key,value);//跳表添加
        return map.put(key, value);
    }

    /**
     * 是否已经达到大小最大的限制
     */
    private boolean isSizeLimit() {
        final int currentSize = this.size();
        return currentSize >= this.sizeLimit;
    }

    /**
     * 实现map接口的方法
     */
    @Override
    @CacheInterceptor(aof = true, evict = true)
    public V remove(Object key) {
        return map.remove(key);
    }

    @Override
    @CacheInterceptor(aof = true)
    public void putAll(Map<? extends K, ? extends V> m) {
        map.putAll(m);
    }

    @Override
    @CacheInterceptor(refresh = true, aof = true)
    public void clear() {
        map.clear();
    }

    @Override
    @CacheInterceptor(refresh = true)
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    @CacheInterceptor(refresh = true)
    public Collection<V> values() {
        return map.values();
    }

    @Override
    @CacheInterceptor(refresh = true)
    public Set<Entry<K, V>> entrySet() {
        return map.entrySet();
    }

}
