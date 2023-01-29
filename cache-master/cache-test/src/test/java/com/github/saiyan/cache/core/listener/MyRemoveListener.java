package com.github.saiyan.cache.core.listener;

import com.github.saiyan.cache.api.ICacheRemoveListener;
import com.github.saiyan.cache.api.ICacheRemoveListenerContext;


public class MyRemoveListener<K,V> implements ICacheRemoveListener<K,V> {

    @Override
    public void listen(ICacheRemoveListenerContext<K, V> context) {
        System.out.println("【删除提示】可恶，我竟然被删除了！" + context.key());
    }

}
