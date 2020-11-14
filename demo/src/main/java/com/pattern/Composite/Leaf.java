package com.pattern.Composite;

/**
 * @author John·Louis
 * @date created on 2020/3/16
 * description:
 */
public class Leaf implements Component{
    String name;
    public Leaf(String name) {
        this.name = name;
    }

    public Component getChild(int i)
    {
        return null;
    }

    @Override
    public void add(Component leaf1) {

    }

    @Override
    public void operation() {
        System.out.println("树叶"+name+"：被访问！");

    }
}
