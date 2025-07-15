package com.louis.longagocode.pattern.bridge;


/**
 * @author John·Louis
 * @date created on 2020/3/12
 * description:
 */
public class ConcreteA implements Implementor {

    @Override
    public void operationImpl() {
        System.out.println("具体实现化(Concrete Implementor)角色被访问" );
    }
}
