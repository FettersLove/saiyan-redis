package com.github.saiyan.cache.core.support.proxy.bs;

import com.github.saiyan.cache.annotation.CacheInterceptor;
import com.github.saiyan.cache.api.ICache;

import java.lang.reflect.Method;


public interface ICacheProxyBsContext {

    //拦截器信息
    CacheInterceptor interceptor();

    //获取代理对象信息
    ICache target();

    //目标对象
    ICacheProxyBsContext target(final ICache target);

    //参数信息
    Object[] params();

    //方法信息
    Method method();

    //方法执行
    Object process() throws Throwable;

}
