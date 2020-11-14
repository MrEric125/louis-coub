package com.pattern.visitor;

/**
 * @author John·Louis
 * @date created on 2020/2/15
 * description:
 */
public class ConcreteElementA implements Element {



    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);

    }

    public String operationA() {
        return "具体元素A的操作。";
    }
}
