package com.louis.longagocode.pattern.Composite;

import java.util.ArrayList;

/**
 * @author John·Louis
 * @date created on 2020/3/16
 * description:
 */
public class Composite implements Component {

    private ArrayList<Component> children = new ArrayList<Component>();

    @Override
    public void add(Component component) {
        children.add(component);

    }

    public Component getChild(int i) {
        return children.get(i);
    }


    @Override
    public void operation() {
        for (Component component : children) {
            component.operation();
        }
    }
}
