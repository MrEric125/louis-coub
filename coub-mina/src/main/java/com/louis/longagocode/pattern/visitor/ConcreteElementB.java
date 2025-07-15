package com.louis.longagocode.pattern.visitor;


/**
 * @author John·Louis
 * @date created on 2020/2/15
 * description:
 */
public class ConcreteElementB implements Element {



    public String operationB() {
        return "具体元素B的操作。";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
