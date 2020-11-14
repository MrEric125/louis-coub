package com.pattern.Composite;

/**
 * @author John·Louis
 * @date created on 2020/3/16
 * description:
 */
public interface Component {
    void add(Component leaf1);

    void operation();
}
