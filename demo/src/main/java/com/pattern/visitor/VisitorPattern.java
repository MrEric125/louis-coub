package com.pattern.visitor;

/**
 * @author John·Louis
 * @date created on 2020/2/15
 * description:
 */
public class VisitorPattern {
    public static void main(String[] args) {
        ObjectStructure os=new ObjectStructure();
        os.add(new ConcreteElementA());
        os.add(new ConcreteElementB());
        Visitor visitor=new ConcreateVisitorA();
        os.accept(visitor);
        System.out.println("------------------------");
        visitor=new ConcreateVisitorB();
        os.accept(visitor);
    }
}
