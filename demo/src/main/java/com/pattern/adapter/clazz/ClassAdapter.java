package com.pattern.adapter.clazz;

/**
 * @author John·Louis
 * @date created on 2020/3/12
 * description:
 */
public class ClassAdapter extends Adapter implements Target {
    @Override
    public void request() {
        specificRequest();

    }
}
