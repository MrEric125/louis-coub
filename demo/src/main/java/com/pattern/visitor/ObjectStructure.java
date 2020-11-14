package com.pattern.visitor;

import org.assertj.core.util.Lists;

import java.util.Iterator;
import java.util.List;

/**
 * @author John·Louis
 * @date created on 2020/2/15
 * description:
 */
public class ObjectStructure {

    public List<Element> elementList = Lists.newArrayList();

    public void accept(Visitor visitor) {
        Iterator<Element> iterator = elementList.iterator();
        while (iterator.hasNext()) {
            iterator.next().accept(visitor);

        }
    }

    public void add(Element element) {
        elementList.add(element);

    }

    public void remove(Element element) {
        elementList.remove(element);
    }
}
