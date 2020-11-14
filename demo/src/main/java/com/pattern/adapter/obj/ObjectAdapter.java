package com.pattern.adapter.obj;

import com.pattern.adapter.clazz.Adapter;
import com.pattern.adapter.clazz.Target;

/**
 * @author John·Louis
 * @date created on 2020/3/12
 * description:
 */
public class ObjectAdapter implements Target {

    private Adapter adapter;

    public ObjectAdapter(Adapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void request() {
        adapter.specificRequest();

    }
}
