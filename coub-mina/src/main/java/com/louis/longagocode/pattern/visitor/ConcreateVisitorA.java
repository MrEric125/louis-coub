package com.louis.longagocode.pattern.visitor;

import com.pattern.visitor.Visitor;

/**
 * @author John·Louis
 * @date created on 2020/2/15
 * description:
 */
public class ConcreateVisitorA implements Visitor {

    @Override
    public void visit(ConcreteElementA elementA) {
        System.out.println("具体访问者A访问-->"+elementA.operationA());

    }

    @Override
    public void visit(ConcreteElementB elementB) {
        System.out.println("具体访问者A访问-->"+elementB.operationB());


    }
}
