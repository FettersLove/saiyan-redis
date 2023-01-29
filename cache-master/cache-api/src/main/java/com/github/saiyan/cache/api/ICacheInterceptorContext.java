package com.github.saiyan.cache.api;

import java.lang.reflect.Method;

/**
 * 拦截器上下文接口
 *
 * （1）get
 * （2）put
 * （3）remove
 * （4）expire
 * （5）evict
 */
public interface ICacheInterceptorContext<K,V> {

    //缓存信息
    ICache<K,V> cache();

    //执行的方法信息
    Method method();

    //执行的参数
    Object[] params();

    //方法执行的结果
    Object result();

    //开始时间
    long startMills();

    //结束时间
    long endMills();

}
