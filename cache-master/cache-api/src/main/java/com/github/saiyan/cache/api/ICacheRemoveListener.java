package com.github.saiyan.cache.api;

/**
 * 删除监听器接口
 */
public interface ICacheRemoveListener<K,V> {

    /**
     * 监听
     */
    void listen(final ICacheRemoveListenerContext<K,V> context);

}
