package com.pattern.visitor;

/**
 * @author John·Louis
 * @date created on 2020/2/15
 * description:
 * 抽象访问者
 */
public interface Visitor {

    void visit(ConcreteElementA elementA);

    void visit(ConcreteElementB elementB);
}
