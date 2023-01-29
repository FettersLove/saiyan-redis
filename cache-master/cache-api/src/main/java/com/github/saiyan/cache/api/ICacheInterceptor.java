package com.github.saiyan.cache.api;

/**
 * 拦截器接口
 *
 * （1）耗时统计
 * （2）监听器
 */
public interface ICacheInterceptor<K,V> {

    //方法执行之前
    void before(ICacheInterceptorContext<K,V> context);

    //方法执行之后
    void after(ICacheInterceptorContext<K,V> context);

}
