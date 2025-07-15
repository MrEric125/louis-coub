package com.louis.longagocode.pattern.bridge;


/**
 * @author John·Louis
 * @date created on 2020/3/12
 * description:
 */
public class RefinedAbstraction extends Abstraction {

    public RefinedAbstraction(Implementor implementor) {
        super(implementor);
    }

    @Override
    public void operation() {
        System.out.println("扩展抽象化(Refined Abstraction)角色被访问");
        implementor.operationImpl();
    }
}
