package com.louis.longagocode.pattern.visitor;


/**
 * @author John·Louis
 * @date created on 2020/2/15
 * description:
 */
public class ConcreateVisitorB implements Visitor {

    @Override
    public void visit(ConcreteElementA elementA) {
        System.out.println("具体访问者B访问-->"+elementA.operationA());
    }

    @Override
    public void visit(ConcreteElementB elementB) {
        System.out.println("具体访问者B访问-->"+elementB.operationB());

    }
}
