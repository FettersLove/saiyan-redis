package com.github.saiyan.cache.core.listener;

import com.github.saiyan.cache.api.ICacheSlowListener;
import com.github.saiyan.cache.api.ICacheSlowListenerContext;


public class MySlowListener implements ICacheSlowListener {

    @Override
    public void listen(ICacheSlowListenerContext context) {
        System.out.println("【慢日志】name: " + context.methodName());
    }

    @Override
    public long slowerThanMills() {
        return 0;
    }

    public static void main(String[] args) {
//        ForkJoinPool
    }

}
